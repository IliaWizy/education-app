# Education app
### Usage

To start an application you need to pass variables to `.env` file.
#### Example:

```agsl
# PostgreSQL
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=education_db
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres

# Email
MAIL_USERNAME= YOUR EMAIL
MAIL_PASSWORD= YOUR GOOGLE APPLICATION PASSWORD
HOUR_EXPIRATION_TOKEN_EMAIL=PT24H

# JWT
JWT_SECRET= YOUR JWT SECRET
JWT_ACCESS_DURATION=PT30M
JWT_REFRESH_DURATION=PT24H
```