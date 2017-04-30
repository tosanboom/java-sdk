Java SDK for Boom API
=====================
The Java SDK for Boom API, provides a comprehensive and yet easy to use SDK for calling Boom's REST API. This SDK requires
at minimum Java 7.

Getting Started
--------
In order to call most services, you would need an instance of `BoomApi`. `BoomApi` encapsulates contextual information
about Boom's REST API. In effect, most of those parameters, encapsulated in a `BoomApi` instance, represents themselves as HTTP
headers.

In order to create a basic instance of `BoomApi`, you can use the `newBuilder()` factory method. There are four common scenarios
when you're creating a `BoomApi`, which will be described in the following sections.

#### Public APIs
Some APIs are *Public* in a sense that they don't require any authentication or authorization related metadata. So, for creating
an instance of `BoomApi` to call a public API, just use the following:
```java
BoomApi boomApi = BoomApi.newBuilder().build()
```

#### OAuth & Access Token
If you're using our OAuth 2.0 flows and already got an *Access Token*, you can build a very minimal and valid instance like
the following:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withAccessToken("your_access_token")
                         .build()
```
By default, all requests will be routed to our *Live* environment. If you're willing to use the *Sandbox* environment, you
can use the `withSandbox` method:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withAccessToken("your_access_token")
                         .setSandbox(true)
                         .build();
```
Current base URL for all services is `https://app.tosanboom.com:4432/v1/`. You also can change it like:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withAccessToken("your_access_token")
                         .withBaseUrl("https://api.tosanboom.com/")
                         .setSandbox(true)
                         .build();
```

#### Session
If you've got a `Session` from our old login APIs, you should provide following parameters to create a `BoomApi` applicable
for protected services:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withBoomToken("Token-Id of App Store")
                         .withAppKey("App-Key or API Key of the application")
                         .withBank(Bank.ANSAR)
                         .withDeviceId("The device id")
                         .setSandbox(true)
                         .withSession("session")
                         .build();
```
#### Card APIs
Some card services don't require the session or access token, since they already got card credentials. So for calling them, create a
`BoomApi` just like the previous scenario but without providing a session:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withBoomToken("Token-Id of App Store")
                         .withAppKey("App-Key or API Key of the application")
                         .withBank(Bank.ANSAR)
                         .withDeviceId("The device id")
                         .setSandbox(true)
                         .build();
```
APIs
--------
Boom provides a catalogue of services through a REST API that empowers fintech applications to rapidly and securely
enhance their digital offerings. Following sections will provide an overview of how to use each category of services.

#### Cards API

#### Deposits API
TODO

#### Bills API
TODO

#### Loans API
TODO

Download
--------
TODO

License
=======
   Copyright 2017 Boom.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.