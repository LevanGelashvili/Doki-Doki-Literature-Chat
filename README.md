# Http Chat

Http based chat written in Kotlin.
Contains 2 applications: Client and Server.


## Installation

Clone this repository and open it in Android Studio.

## Usage

1. Run server app on one emulator
2. Enable tcp port forwarding:
```bash
./adb -s emulator-5554 forward tcp:5000 tcp:5000
```
3. Start second emulator and run client app on both emulators


## Additional Features

* MVP + Clean Architecture in both applications
* Runtime update of messages and chats
