package com.reksoft.orm;

import com.reksoft.annotation.Column;
import com.reksoft.annotation.Id;
import com.reksoft.annotation.Table;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JabaORM {

  private static final String WHERE = " WHERE ";

  private final String url;
  private final String username;
  private final String password;
  private CachedRowSet rowSet;

  public JabaORM(String url, String username, String password) throws SQLException {
    this.url = url;
    this.username = username;
    this.password = password;
    this.rowSet = getRowSet();
  }

  public <T> List<T> getAll(Class<T> clazz)
      throws SQLException, InstantiationException, IllegalAccessException {
    Table tableAnnotation = clazz.getAnnotation(Table.class);
    checkTableAnnotationsOnPresent(clazz, tableAnnotation);

    Field[] fields = clazz.getDeclaredFields();

    String sql = "SELECT * FROM " + tableAnnotation.name();
    rowSet.setCommand(sql);
    rowSet.execute();

    List<T> entities = new ArrayList<>();
    while (rowSet.next()) {
      T entity = clazz.newInstance();
      getEntityFromRowSet(fields, entity);
      entities.add(entity);
    }

    return entities;
  }

  public <T> T getById(Class<T> clazz, Object id)
      throws SQLException, InstantiationException, IllegalAccessException {
    Table tableAnnotation = clazz.getAnnotation(Table.class);
    checkTableAnnotationsOnPresent(clazz, tableAnnotation);

    Field[] fields = clazz.getDeclaredFields();
    Field idField = findIdField(fields);
    checkIdAnnotationsOnPresent(idField, clazz);

    String idColumnName = idField.getAnnotation(Column.class).name();

    String sql = "SELECT * FROM " + tableAnnotation.name() + WHERE + idColumnName + " = " + id;
    rowSet.setCommand(sql);
    rowSet.execute();

    T entity = clazz.newInstance();
    while (rowSet.next()) {
      getEntityFromRowSet(fields, entity);
    }

    return entity;
  }

  public <T> void deleteById(Class<T> clazz, Object id) throws SQLException {
    Table tableAnnotation = clazz.getAnnotation(Table.class);
    checkTableAnnotationsOnPresent(clazz, tableAnnotation);

    Field[] fields = clazz.getDeclaredFields();
    Field idField = findIdField(fields);
    checkIdAnnotationsOnPresent(idField, clazz);

    String idColumnName = idField.getAnnotation(Column.class).name();

    String sql = "DELETE FROM " + tableAnnotation.name() + WHERE + idColumnName + " = " + id;
    rowSet.setCommand(sql);
    rowSet.execute();

    System.out.println("Successfully deleted entity with ID = " + id);
  }



  public <T> void save(T entity) throws SQLException, IllegalAccessException, NoSuchFieldException {
    Class<?> clazz = entity.getClass();
    Table tableAnnotation = clazz.getAnnotation(Table.class);
    checkTableAnnotationsOnPresent(clazz, tableAnnotation);

    Field[] fields = clazz.getDeclaredFields();
    Map<String, String> valuseMap = new HashMap<>();
    for (Field field : fields) {
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (column != null && !field.isAnnotationPresent(Id.class)) {
        Field fieldValue = clazz.getDeclaredField(column.name());
        fieldValue.setAccessible(true);
        String value = fieldValue.get(entity).toString();

        if (fieldValue.getType() == String.class) {
          valuseMap.put(column.name(), "'" + value + "'");
        } else {
          valuseMap.put(column.name(), value);
        }
      }

    }
    StringBuilder sql = new StringBuilder("INSERT INTO " + tableAnnotation.name() + "(");
    StringBuilder columnsBuilder = new StringBuilder();
    StringBuilder valuesBuilder = new StringBuilder();

    for (Map.Entry<String, String> entry : valuseMap.entrySet()) {
      if (!columnsBuilder.isEmpty()) {
        columnsBuilder.append(", ");
        valuesBuilder.append(", ");
      }
      columnsBuilder.append(entry.getKey());
      valuesBuilder.append(entry.getValue());
    }

    sql.append(columnsBuilder)
        .append(") VALUES (")
        .append(valuesBuilder)
        .append(") ");

    rowSet.setCommand(sql.toString());
    rowSet.execute();

    System.out.println("Successfully create entity: " + entity);
  }

  public <T> void update(T entity)
      throws SQLException, IllegalAccessException, NoSuchFieldException {
    Class<?> clazz = entity.getClass();
    Table tableAnnotation = clazz.getAnnotation(Table.class);
    checkTableAnnotationsOnPresent(clazz, tableAnnotation);

    Field[] fields = clazz.getDeclaredFields();
    Field idField = findIdField(fields);
    checkIdAnnotationsOnPresent(idField, clazz);

    String idColumnName = idField.getAnnotation(Column.class).name();
    Object idColumnValue = null;

    StringBuilder sql = new StringBuilder("UPDATE " + tableAnnotation.name() + " SET ");
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (field.isAnnotationPresent(Id.class)) {
        Field fieldValue = clazz.getDeclaredField(column.name());
        fieldValue.setAccessible(true);
        idColumnValue = fieldValue.get(entity);
      }
      if (column != null && !field.isAnnotationPresent(Id.class)) {
        Field fieldValue = clazz.getDeclaredField(column.name());
        fieldValue.setAccessible(true);
        String value = fieldValue.get(entity).toString();

        if (fieldValue.getType() == String.class) {
          sql.append(column.name()).append(" = ").append("'").append(value).append("'");
        } else {
          sql.append(column.name()).append(" = ").append(value);
        }

        if (i < fields.length - 1) {
          sql.append(", ");
        }
      }
    }

    sql.append(WHERE).append(idColumnName).append(" = ").append(idColumnValue);

    rowSet.setCommand(sql.toString());
    rowSet.execute();

    System.out.println("Successfully create entity with ID = " + idColumnValue);
  }

  private static <T> void checkTableAnnotationsOnPresent(Class<T> clazz, Table tableAnnotation) {
    if (tableAnnotation == null) {
      throw new IllegalArgumentException(
          "Class " + clazz.getName() + " is not annotated with @Table");
    }
  }

  private static void checkIdAnnotationsOnPresent(Field idField, Class<?> clazz) {
    if (idField == null) {
      throw new IllegalArgumentException(
          "Class " + clazz.getName() + " does not have a field annotated with @Id");
    }
  }

  private static Field findIdField(Field[] fields) {
    Field idField = null;
    for (Field field : fields) {
      field.setAccessible(true);
      Id idAnnotation = field.getAnnotation(Id.class);
      if (idAnnotation != null) {
        idField = field;
        break;
      }
    }
    return idField;
  }

  private <T> void getEntityFromRowSet(Field[] fields, T entity)
      throws SQLException, IllegalAccessException {
    for (Field field : fields) {
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        String columnName = column.name();
        Object value = rowSet.getObject(columnName);
        field.set(entity, value);
      }
    }
  }

  private CachedRowSet getRowSet() throws SQLException {
    if (rowSet == null) {
      RowSetFactory factory = RowSetProvider.newFactory();
      rowSet = factory.createCachedRowSet();

      rowSet.setUrl(url);
      rowSet.setUsername(username);
      rowSet.setPassword(password);
    }
    return rowSet;
  }
}
