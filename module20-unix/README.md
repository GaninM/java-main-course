# Модуль 20: Основы Unix
- Установите ОС Linux (это может быть ваша собственная ОС на базе Linux или установка ее путем установки ОС VirtualBox + Unix на ваш компьютер с Windows).
- Все ваши вводы/выводы с консоли должны отправляться вашему ментору после завершения всех задач. Итак, позаботьтесь об этом, прежде чем приступать к выполнению задачи (подумайте, как вы получите эту информацию).
 
Файлы с логами находятся в текущей папке. Чтобы изучить логи в удобном виде необходимо их открыть в Unix системе командой cat или другим удобным способом. Например можно воспользоваться Windows PoweShell используя команду gc <имя файла>. Пример: gc module20-common-comands.log

Задания:

[Простые команды:](module20-common-comands.log)
- Распечатайте на экране надпись «CDP JaMP»
  ```bash
  echo "CDP JaMP"
  ```
- Распечатайте информацию об ОС (имя, ядро и т. д.)
    ```bash
  uname -a
  ```
- Распечатайте текущий путь
  ```bash
  pwd
  ```
- Распечатайте текущую дату
  ```bash
  date +"%Y-%m-%d"
  ```
- Распечатайте текущее время
  ```bash
  date +"%H:%M:%S"
  ```


[Команды навигации:](module20-navigation.log)
- Распечатайте полный путь к вашему домашнему каталогу
  ```bash
  $HOME
  ```
- Перейдите в домашний каталог
  ```bash
  cd ~
  ```
- Получите все файлы в текущем каталоге
  ```bash
  ls
  ```
- Создайте новую папку «test»
  ```bash
  mkdir test
  ```
- Переименуйте папку «test» в «newTest»
  ```bash
  mv test newTest
  ```
- Перейдите в папку «newTest»
  ```bash
  cd newTest
  ```
- Получите текущую дату и сохраните результат в файл «nowFile»
  ```bash
  date > nowFile
  ```
- Скопируйте файл «nowFile». Новое имя файла — «copyOfNowFile»
  ```bash
  cp nowFile copyOfNowFile
  ```
- Получите информацию обо всех файлах (включая дату создания, автора и т. д.)
  ```bash
  ls -l
  ```
- Создайте пустой файл «emptyFile» без текстового редактора
  ```bash
  touch emptyFile
  ```
- Вернитесь назад в домашний каталог
  ```bash
  cd ~
  ```
- Удалите папку «newTest» с ее содержимым
  ```bash
  rm -r newTest
  ```


[Права доступа (Permissions):](module20-permissions.log)
- Получите все файлы в текущем каталоге
  ```bash
  ls
  ```
- Создайте новую папку «permission»
  ```bash
  mkdir permission
  ```
- Перейдите в этот каталог
  ```bash
  cd permission
  ```
- Создайте новый файл «secretFile» с содержимым «This is very secret info»
  ```bash
  echo "This is very secret info" > secretFile
  ```
- Измените разрешение этого файла, чтобы никто не мог его прочитать
  ```bash
  chmod 000 secretFile
  ```
- Получите список файлов в текущем каталоге с информацией о разрешениях
  ```bash
  ls -l
  ```
- Попробуйте прочитать информацию из файла
  ```bash
  cat secretFile
  ```


[Управление пользователями и группами:](module20-users-and-groups.log)
- Распечатайте текущего пользователя
  ```bash
  whoami
  ```
- Измените свой пароль
  ```bash
  passwd
  ```
- Добавьте новых пользователей «readUser» и «editUser»
  ```bash
  sudo adduser readuser
  sudo adduser edituser
  ```
- Добавьте новые группы «readers» и «editors»
  ```bash
  sudo groupadd readers
  sudo groupadd editors
  ```
- Добавьте пользователя «readUser» в группу «readers» и «editUser» в группу «editors»
  ```bash
  sudo usermod -aG readers readuser
  sudo usermod -aG editors edituser
  ```
- Создайте «read» и «edit» каталоги с любыми файлами внутри (это могут быть файлы из предыдущих задач)
  ```bash
  mkdir read edit
  echo "Read only file" > read/readfile.txt
  echo "Editable file" > edit/editfile.txt
  ```
- Предоставьте группам права доступа к каталогам (доступ только на чтение для группы «readers» к каталогу «read» и полный доступ для группы «editors» к каталогу «edit»)
  ```bash
  sudo chown :readers read
  sudo chmod 750 read
  sudo chown :editors edit
  sudo chmod 770 edit
  ```
- Попробуйте переключаться между пользователями и читать информацию из файлов
  ```bash
  su readuser
  cat read/readfile.txt
  echo "New line" > edit/editfile.txt
  ```


[Процессы:](module20-process.log)
- Получите список всех запущенных процессов
  ```bash
  ps aux
  ```
- Получите список всех запущенных процессов с использованием процессора и памяти
  ```bash
  ps aux
  ```
- Получите список всех запущенных процессов вашего пользователя
  ```bash
  ps -u $(whoami)
  ```
- Получите динамический список процессов, которыми в данный момент управляет ядро Linux (CPU, использование памяти и т. д.), в режиме реального времени
  ```bash
  top
  ```
- Отсортируйте предыдущий список по использованию памяти
  ```bash
  ps aux --sort=-%mem
  ```
