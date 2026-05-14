# Инициализация базы данных FIFA Card Game 2026

## Как это работает

При запуске приложения через `build_and_run.ps1`:

1.  **Компиляция** - Все Java файлы скомпилированы, включая новый `DatabaseInitializer`
2.  **Запуск приложения** - `Main.java` запускается и немедленно вызывает `DatabaseInitializer.initialize()`
3.  **Инициализация схемы** - Выполняется `init.sql` из `src/main/resources/db/init.sql`:
   - Создаются таблицы `countries`, `players`, `matches`
4.  **Загрузка данных** - Выполняется `seed.sql` из `src/main/resources/db/seed.sql`:
   - **DELETE** - очищаются все существующие данные (matches, players, countries)
   - **INSERT** - загружаются 240 игроков из 16 стран с их фотографиями

## Структура БД

### Таблица countries
```sql
- id (Primary Key)
- name (VARCHAR 100)
- ovr_attack (INT)
- ovr_defense (INT)
```

### Таблица players
```sql
- id (Primary Key)
- name (VARCHAR 100)
- country_id (Foreign Key → countries.id)
- position (VARCHAR 10) - GK, CB, LB, RB, CM, CDM, CAM, LW, RW, ST, CF
- overall (INT) - 63-91
- is_starter (BOOLEAN)
- photo_path (VARCHAR 255) - путь к фото (например: 'argentina_l_messi_23.png')
```

## Данные

### Страны (16):
1. Kazakhstan - 15 игроков (63-72 overall)
2. Argentina - 15 игроков (73-90 overall)
3. Brazil - 15 игроков (73-86 overall)
4. France - 15 игроков (80-91 overall)
5. Germany - 15 игроков (77-86 overall)
6. Spain - 15 игроков (79-87 overall)
7. England - 15 игроков (74-91 overall)
8. Portugal - 15 игроков (75-88 overall)
9. Netherlands - 15 игроков (74-87 overall)
10. Senegal - 15 игроков (70-82 overall)
11. Mexico - 15 игроков (69-84 overall)
12. USA - 15 игроков (69-78 overall)
13. Morocco - 15 игроков (71-85 overall)
14. Australia - 15 игроков (68-76 overall)
15. South Korea - 15 игроков (70-84 overall)
16. Japan - 15 игроков (68-84 overall)

**Всего: 240 игроков**

## Как изменить данные

### Чтобы добавить новых игроков:
1. Отредактируйте `src/main/resources/db/seed.sql`
2. Добавьте новые `INSERT INTO players` строки
3. Убедитесь что `photo_path` указывает на существующую фотографию в `resources/images/players/`
4. Запустите `build_and_run.ps1` - БД будет очищена и перезагружена с новыми данными

### Чтобы изменить структуру БД:
1. Отредактируйте `src/main/resources/db/init.sql`
2. Добавьте новые таблицы или измените существующие
3. Убедитесь что `seed.sql` совместима с новой схемой
4. Запустите `build_and_run.ps1`

## Файлы изменены/созданы

-  **Создан** `src/com/fifa/util/DatabaseInitializer.java` - класс для инициализации БД
-  **Изменён** `src/com/fifa/Main.java` - добавлена инициализация БД при запуске

## Как использовать

```powershell
cd 'c:\kuka\fifa_card_game'
.\build_and_run.ps1
```

При запуске вы увидите в консоли:
```
 Инициализация базы данных...
 Создание схемы БД...
   Схема создана
 Загрузка данных игроков в БД...
   Данные загружены (240 игроков из 16 стран)
 База данных успешно инициализирована!
```

Затем приложение запустится с полностью инициализированной БД с 240 игроками, готовыми к использованию.

## Технические детали

- `DatabaseInitializer` читает SQL файлы из ресурсов приложения
- Пропускает комментарии (строки с `--`) и пустые строки
- Разбивает SQL скрипт на отдельные команды по `;`
- Каждая команда выполняется отдельно, ошибки одной не прерывают выполнение остальных
- Фотографии загружаются через `ImageLoader.loadPlayer(photo_path)` из `resources/images/players/`