# Education app
### Usage

To start an application you need to pass variables to `.env` file.
#### Example:

```agsl
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=education_db
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres
```

To send activation email through google, you need to:
- Enable two-factor authentication
- Create an application password (16 letters without spaces)
- Add data to the .env file, for example:

```agsl
EMAIL_USERNAME=vasyasuperhero@gmail.com
EMAIL_PASSWORD=abcdabcdabcdabcd
```
