<a href="https://www.twilio.com">
  <img src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg" alt="Twilio" width="250" />
</a>

# Call Tracking on Java Servlets

[![Java Servlet CI](https://github.com/TwilioDevEd/call-tracking-servlets/actions/workflows/gradle.yml/badge.svg)](https://github.com/TwilioDevEd/call-tracking-servlets/actions/workflows/gradle.yml)

Use Twilio to track the effectiveness of your different marketing campaigns.
Learn how call tracking helps organizations in [these Twilio customer
stories](https://www.twilio.com/use-cases/call-tracking).

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/call-tracking/java/servlets)!

## Quickstart

### Create a TwiML App

This project is configured to use a **TwiML App**, which allows us to easily set
the voice URLs for all Twilio phone numbers we purchase in this app.

Create a new TwiML app [in the Twilio Console](https://console.twilio.com/us1/develop/voice/manage/twiml-apps?frameUrl=%2Fconsole%2Fvoice%2Ftwiml%2Fapps%3Fx-target-region%3Dus1) and use
its `Sid` as the `TWIML_APPLICATION_SID` environment variable wherever you run
this app.

Learn more about how to [create a TwiML app](https://support.twilio.com/hc/en-us/articles/223180928-How-Do-I-Create-a-TwiML-App-).

## Set up

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 8
- [PostgreSQL](https://www.postgresql.org/)
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config Value | Description                                                                                                                                                  |
| :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Account Sid  | Your primary Twilio account identifier - find this [in the Console](https://www.twilio.com/console).                                                         |
| Auth Token   | Used to authenticate - [just like the above, you'll find this here](https://www.twilio.com/console).                                                         |
| Phone number | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://console.twilio.com/us1/develop/phone-numbers/manage/search?frameUrl=%2Fconsole%2Fphone-numbers%2Fsearch%3Fx-target-region%3Dus1&currentFrameUrl=%2Fconsole%2Fphone-numbers%2Fsearch%3FisoCountry%3DUS%26searchTerm%3D%26searchFilter%3Dleft%26searchType%3Dnumber%26x-target-region%3Dus1%26__override_layout__%3Dembed%26bifrost%3Dtrue) |

### Local development

After the above requirements have been met:

1. Clone this repository and `cd` into its directory:

   ```bash
   git clone git@github.com:TwilioDevEd/call-tracking-servlets.git
   cd call-tracking-servlets
   ```

1. Create the database.

   ```bash
   createdb call-tracking-servlets
   ```

   _The application uses PostgreSQL as the persistence layer. If you
   don't have it already, you should install it. The easiest way is by
   using [Postgres.app](http://postgresapp.com/)._

1. Copy the sample configuration file and edit it to match your configuration.

   ```bash
   cp .env.example .env
   ```
   See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

1. Execute the migrations.

   ```bash
   ./gradlew flywayMigrate
   ```

1. Run the application.

   ```bash
   ./gradlew appRun
   ```

1. Check it out at [http://localhost:8080](http://localhost:8080)

Additionally, in order to let Twilio Phone numbers use the callback endpoint we
exposed, our development server will need to be publicly accessible.
[We recommend using ngrok to solve this problem](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html).

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.
