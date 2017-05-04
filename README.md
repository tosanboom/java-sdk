Java SDK for Boom API [![Build Status](https://api.travis-ci.org/tosanboom/java-sdk.svg?branch=master)](https://api.travis-ci.org/tosanboom/java-sdk)
=====================
The Java SDK for Boom API, provides a comprehensive and yet easy to use SDK for calling Boom's REST API. This SDK requires
at minimum Java 7.

Download
--------
Download [the latest JAR][latest-jar] or grab via Maven:


    <dependency>
      <groupId>ir.boommarket</groupId>
      <artifactId>java-sdk</artifactId>
      <version>0.0.1</version>
    </dependency>

or Gradle:

    compile 'ir.boommarket:java-sdk:0.0.1'

Getting Started
--------
In order to call all services, you would need an instance of `BoomApi`. `BoomApi` encapsulates contextual information and parameters
about Boom's REST API. In effect, most of those parameters manifests themselves as HTTP headers.

In order to create a basic instance of `BoomApi`, you can use the `newBuilder()` factory method. There are four common scenarios
when you're creating a `BoomApi`, which will be discussed in the following sections.

#### Public APIs
Some APIs are *Public* in a sense that they don't require any authentication or authorization related metadata. So, for creating
an instance of `BoomApi` to call a public API, just use the following:
```java
BoomApi boomApi = BoomApi.forPublicApi();
```

#### OAuth & Access Token
If you're using our OAuth 2.0 flows and already got an *Access Token*, you can build a very minimal and valid instance like
the following:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withAccessToken("your_access_token")
                         .build();
```
By default, all requests will be routed to our *Live* environment. If you're willing to use the *Sandbox* environment, you
can use the `setSandbox` method:
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
                         .withBoomToken("boom_token")
                         .withAppKey("app_key")
                         .withBank(Bank.ANSAR)
                         .withDeviceId("device_id")
                         .setSandbox(true)
                         .withSession("session")
                         .build();
```
#### Card APIs
Some card services don't require the session or access token, since they already got card credentials. So for calling them, create a
`BoomApi` just like the previous scenario but without providing a session:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withBoomToken("boom_token")
                         .withAppKey("app_key")
                         .withBank(Bank.ANSAR)
                         .withDeviceId("device_id")
                         .setSandbox(true)
                         .build();
```
APIs
--------
Boom provides a catalogue of services through a REST API that empowers fintech applications to rapidly and securely
enhance their digital offerings. Following sections will provide an overview of how to use each category of services.

### Accounts API
Main entry point for all account related services is the `Accounts` class. This class will provide a static method for 
each service. Those static services usually accept two arguments: The first one is specific to service itself and the
second one is an instance of `BoomApi`.

#### Bank Login
Provided that you've got a username/password for your internet bank, you can login with them and hopefully get a
session:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withBoomToken("your_boom_token")
                         .withDeviceId("your_device_id")
                         .withAppKey("app_key")
                         .withBank(Bank.ANSAR)
                         .setSandbox(true)
                         .build();

BankLoginRequest request = new BankLoginRequest("username", "password");
BankLoginResponse response = Accounts.bankLogin(request, boomApi);
System.out.println(response.sessionId());
```

#### Account Info
In order to see personal information of an account holder, use the `Accounts.getInfo` static method:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withAccessToken("your_access_token")
                         .build();

AccountInfoRequest request = AccountInfoRequest.withoutAddress(); 
// Passing null is equivalent to withoutAddress method call
AccountInfo info = Accounts.getInfo(request, boomApi);
System.out.println(info.name());
```
If you want to get list of ten addresses for the account holder, create a request like this:
```java
AccountInfoRequest request = AccountInfoRequest.newBuilder().showAddresses().withLength(10).build();
```

### Banks API
Main entry point for all bank related services is the `Banks` class which follows the general theme of whole SDK.

#### Get Branches
In order to get a list of first ten branches of the given bank:
```java
BoomApi boomApi = BoomApi.forPublicApi();
BranchListRequest request = new BranchListRequest(Bank.ANSAR);
BranchList branches = Banks.getBranches(request, boomApi);
```
You can also provide a length/offset combination for paginating through the result:
```java
// offset = 5, length = 10
BranchListRequest request = new BranchListRequest(Bank.ANSAR, 5, 10);
```

### Cards API
`Cards` class is the main entry point for all card related services with a few static methods.

#### Card Balance
In order to get your card balance, create an instance of `CardBalanceRequest` by passing your card credentials (pan, 
pin, cvv2 and expiration date) and then pass it to `Cards.getBalance` along with a `BoomApi`:
```java
BoomApi boomApi = BoomApi.newBuilder()
                         .withBoomToken("boom_token")
                         .withDeviceId("device_id")
                         .withAppKey("app_key")
                         .withBank(Bank.ANSAR)
                         .setSandbox(true)
                         .build();

CardBalanceRequest request = new CardBalanceRequest("pan", "pin", "cvv2", "expiration_date");
CardBalance response = Cards.getBalance(request, boomApi);
System.out.println(response.availableBalance());
```

### Deposits API
TODO

### Bills API
TODO

### Loans API
TODO

### ACH (i.e. Paya) API
TODO

License
=======
   Copyright 2017 Boommarket.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
[latest-jar]: https://search.maven.org/remote_content?g=ir.boommarket&a=java-sdk&v=LATEST