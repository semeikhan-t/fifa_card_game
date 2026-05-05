# Project Status — FIFA Card Game 2026

## 1. Что уже сделано

### Инфраструктура
- PostgreSQL 18 установлен по пути `C:\Program Files\PostgreSQL\18`
- База данных `fifa26` создана, таблицы `countries`, `players`, `matches` инициализированы (`init.sql`)
- Данные загружены через `seed.sql` — **16 стран** и **19 игроков** (Kazakhstan: 11, Argentina: 8)
- Пароль пользователя `postgres` установлен на `helloworld` (совпадает с `DatabaseManager.java`)
- JavaFX SDK 21.0.2 лежит в `javafx-sdk-21.0.2/`
- Библиотеки `lib/postgresql-42.6.0.jar` и `lib/json-20240303.jar` на месте

### Исходный код (src/com/fifa/)
- **Launcher.java** → точка входа, вызывает `Main.main()`
- **Main.java** → JavaFX Application, загружает `MainMenu.fxml`
- **model/** → `Entity`, `Player`, `Team`, `MatchResult`, `MatchEvent`
- **dao/** → `DAO`, `AbstractDAO`, `PlayerDAO`, `TeamDAO`, `MatchDAO`
- **service/** → `MatchService` (симуляция матча), `SquadService` (загрузка состава)
- **controller/** → `MainMenuController`, `CountrySelectionController`, `SquadController`, `OpponentSelectionController`, `MatchController`, `ResultController`, `StatsController`, `PlayerCardController`
- **util/** → `DatabaseManager`, `SceneManager`, `ImageLoader`

### Ресурсы
- **FXML** (8 файлов): `MainMenu`, `CountrySelection`, `Squad`, `OpponentSelection`, `Match`, `Result`, `Stats`, `PlayerCard`
- **CSS**: `resources/css/style.css` — тёмная тема, glassmorphism, gold/silver карточки
- **Изображения**: `resources/images/pitch.png`, `resources/images/wc2026.png`
- **Фото игроков**: `src/main/resources/images/players/` — **270 файлов** (формат `country_name_id.png`)
- **Флаги**: `src/main/resources/images/flags/` — **16 файлов** (формат `country.png`)
- **Placeholder**: `src/main/resources/images/placeholder_player.png`

### Скомпилированные файлы
- Папка `out/` содержит скомпилированные `.class` файлы, CSS, FXML, images

### Приложение запускается
- Команда запуска работает, `MainMenuController initialized!` выводится в консоль

## 2. Текущие проблемы и недоработки

### 🔴 Критические
1. **seed.sql неполный** — загружены игроки только для Kazakhstan (11) и Argentina (8). Остальные 14 стран (**Brazil, France, England, Germany, Spain, Portugal, Netherlands, Italy, Japan, USA, Morocco, Croatia, Uruguay, Belgium**) не имеют ни одного игрока в БД. Без них игра не работает — выбор этих стран даст пустой состав.
2. **Поле `photo_path` не заполнено** — в seed.sql нет `photo_path` ни у одного игрока. Колонка `photo_path` существует в таблице `players`, но `PlayerDAO` не читает её, а `Player.java` не имеет поля `photoPath`.
3. **Фото скачаны с ошибками** — многие фото привязаны к неправильным странам (например, `australia_*.png` содержит польских игроков, `japan_*.png` — коста-риканских, `kazakhstan_*.png` — украинских, `south_korea_*.png` — перуанских, `usa_*.png` — французских). Предыдущий агент скачал фото из API FUTDB, но привязка страна→игрок сломана.

### 🟡 Средние
4. **`SquadController.createPlayerCard()` не использует `PlayerCardController`** — вместо этого напрямую через `card.lookup()` ищет элементы. Метод `setPlayer()` в `PlayerCardController` никогда не вызывается.
5. **`ImageLoader` загружает из classpath** (`getResourceAsStream`), но фото лежат в `src/main/resources/images/`, а `out/images/` — это другая папка. Нужно проверить, что при компиляции фото копируются в `out/`.
6. **`CountrySelectionController` не показывает флаги** — в карточке страны только текстовая `Label`, нет `ImageView` для флага.
7. **CSS-класс `player-card-bg-gold` описан в `PlayerCardController.applyCardTheme()`, но не определён в `style.css`** — есть только `.player-card-bg` (gold по умолчанию) и `.player-card-bg-silver`.
8. **`player-card-bg-bronze`** используется в `PlayerCardController`, но нет в CSS.

### 🟢 Мелкие
9. **`PlayerDAO.findAll()` возвращает `null`** — заглушка, нужна реализация.
10. **`MatchDAO.save()` и `TeamDAO.save()/delete()` — заглушки**.
11. **Нет отдельного `SceneManager` для передачи `countryName` в `PlayerCardController`** — `setPlayer()` ожидает `countryName`, но нигде не передаётся.

## 3. Структура проекта

```
c:\project\fifa_card_game\
├── pom.xml                     # Maven (Java 21, JavaFX 21.0.2, PostgreSQL, JSON)
├── Launcher.java / Main.java   # Точка входа
├── javafx-sdk-21.0.2/          # JavaFX SDK
├── lib/                        # postgresql-42.6.0.jar, json-20240303.jar
├── out/                        # Скомпилированные файлы
├── resources/
│   ├── css/style.css
│   ├── db/init.sql, seed.sql
│   ├── fxml/ (8 файлов)
│   └── images/pitch.png, wc2026.png
├── src/
│   ├── com/fifa/
│   │   ├── Launcher.java, Main.java
│   │   ├── controller/ (8 контроллеров)
│   │   ├── dao/ (DAO, AbstractDAO, PlayerDAO, TeamDAO, MatchDAO)
│   │   ├── model/ (Entity, Player, Team, MatchResult, MatchEvent)
│   │   ├── service/ (MatchService, SquadService)
│   │   └── util/ (DatabaseManager, SceneManager, ImageLoader)
│   └── main/resources/images/
│       ├── players/ (270 PNG файлов)
│       ├── flags/ (16 PNG файлов)
│       └── placeholder_player.png
└── DownloadAssets.java          # Утилита скачивания (не часть приложения)
```

## 4. Версии и подключения

| Компонент | Версия | Путь |
|-----------|--------|------|
| Java | 21.0.10 | системный PATH |
| JavaFX | 21.0.2 | `javafx-sdk-21.0.2/` |
| PostgreSQL | 18 | `C:\Program Files\PostgreSQL\18\` |
| БД | fifa26 | `localhost:5432`, user: `postgres`, pass: `helloworld` |

## 5. Как запустить

### Запуск PostgreSQL (если не запущен)
```powershell
& "C:\Program Files\PostgreSQL\18\bin\pg_ctl.exe" start -D "C:\Program Files\PostgreSQL\18\data"
```

### Компиляция (из корня проекта)
```powershell
javac --module-path "javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\postgresql-42.6.0.jar" -d out -sourcepath src src\com\fifa\Launcher.java
```

### Запуск приложения
```powershell
java --module-path "javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp "out;lib\postgresql-42.6.0.jar" com.fifa.Launcher
```

## 6. Состояние базы данных

```
countries: 16 записей (все 16 стран)
players:   19 записей (Kazakhstan: 11, Argentina: 8, остальные 14 стран: 0 игроков!)
matches:   0 записей
```

## 7. Задачи для следующего агента (по приоритету)

### Шаг 1: Заполнить `seed.sql` игроками для всех 16 стран
- Сейчас игроки есть только у Kazakhstan (11) и Argentina (8). Нужно добавить по **11 стартовых + 5-7 запасных** игроков для каждой из оставшихся 14 стран.
- Распределение позиций на каждую страну: 1 GK, 4 DEF, 3 MID, 3 FWD (стартовые) + запасные.
- **Страны без игроков**: Brazil, France, England, Germany, Spain, Portugal, Netherlands, Italy, Japan, USA, Morocco, Croatia, Uruguay, Belgium.
- ID стран в БД (важно для `country_id`):
  - 1=Kazakhstan, 2=Brazil, 3=France, 4=Argentina, 5=England, 6=Germany, 7=Spain, 8=Portugal, 9=Netherlands, 10=Italy, 11=Japan, 12=USA, 13=Morocco, 14=Croatia, 15=Uruguay, 16=Belgium
- После написания — выполнить: `& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -d fifa26 -f "resources\db\seed.sql"` (предварительно очистить таблицу players: `TRUNCATE players RESTART IDENTITY CASCADE;`)

### Шаг 2: Добавить `photoPath` в модель `Player`
- Добавить поле `private String photoPath;` в `Player.java` с getter/setter
- Обновить конструктор для поддержки `photoPath`
- Обновить `PlayerDAO.findById()` и `PlayerDAO.findByCountry()` — добавить `rs.getString("photo_path")`

### Шаг 3: Заполнить `photo_path` в seed.sql
- У каждого игрока в `seed.sql` указать `photo_path` — имя файла из `src/main/resources/images/players/`
- Пример: для Lionel Messi файл `argentina_l__messi_154.png`
- **ВАЖНО**: фото для многих стран привязаны неправильно (см. раздел "Критические проблемы"). Нужно либо:
  - (а) Пересопоставить имена файлов с реальными игроками, либо
  - (б) Использовать placeholder для стран, где фото неверные

### Шаг 4: Интегрировать `PlayerCardController` в `SquadController`
- В `SquadController.createPlayerCard()` (строка 134) после загрузки FXML вызвать `PlayerCardController.setPlayer(player, countryName, photoFileName)` вместо ручного `card.lookup()`
- Для этого нужно получить `countryName` — можно через `TeamDAO.findById(player.getCountryId()).getName()`

### Шаг 5: Добавить флаги в экран выбора страны
- В `CountrySelectionController.createCountryCard()` добавить `ImageView` с флагом
- Использовать `ImageLoader.loadFlag(team.getName().toLowerCase())`

### Шаг 6: Убедиться что ресурсы копируются при компиляции
- Фото игроков лежат в `src/main/resources/images/` — при ручной компиляции через `javac` они НЕ копируются автоматически
- Нужно либо:
  - (а) Скопировать `src/main/resources/images/` в `out/images/`, либо
  - (б) Изменить `ImageLoader` чтобы загружал файлы с файловой системы, а не из classpath

### Шаг 7: Добавить недостающие CSS-классы
- Добавить в `style.css`:
  - `.player-card-bg-gold` (сейчас gold — это дефолтный `.player-card-bg`, но `PlayerCardController` ожидает отдельный класс)
  - `.player-card-bg-bronze` (для overall < 80)
  - `.country-card` (используется в `CountrySelectionController`)

### Шаг 8: Перекомпиляция
- После всех изменений перекомпилировать:
```powershell
javac --module-path "javafx-sdk-21.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp "lib\postgresql-42.6.0.jar" -d out -sourcepath src src\com\fifa\Launcher.java
```
- Скопировать ресурсы:
```powershell
Copy-Item -Path "resources\css" -Destination "out\css" -Recurse -Force
Copy-Item -Path "resources\fxml" -Destination "out\fxml" -Recurse -Force
Copy-Item -Path "resources\images" -Destination "out\images" -Recurse -Force
Copy-Item -Path "src\main\resources\images\*" -Destination "out\images\" -Recurse -Force
```
- Запустить и проверить

### Шаг 9 (бонус): Улучшения
- Реализовать `PlayerDAO.findAll()`
- Добавить обработку ошибок при подключении к БД (показывать Alert вместо краша)
- Добавить звуковые эффекты для голов
- Добавить анимации переходов между экранами
