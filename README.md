# Coin Trends
Coin Trends is showcase or blueprint Android project that is trying to leverage what MVVP architecture does in a more effective way using latest frameworks and technologies available. 

Next are used:

- Retrofit2
- Dagger2 
- androidx
- Room Database
- Kotlin coroutines

Before going deeper how everything works I would like to explain what Coin Trends app does. 'Coin' in the name is not for nothing because applicaiton gets information about all the coins that
are on trade at Wavesplatform DEX (decentralized exchange). This DEX is chosen because REST API endpoints are free and there is no limit on access. If you want to create similar application that
works with different exchanges, you can achieve that relatively simply by changing exhange's specifics in Model layer. Since all data are static (in this version), room database is used 
for storing them for later use.
   
## Architecture

[ToDo]



## Screenshots 

<img src="img/Screenshot1.png" alt="Screenshot1" width="360"/>

![Alt text](img/Screenshot1.png?raw=true "Screenshot1" =360x640)
![Alt text](img/Screenshot2.png?raw=true "Screenshot2" =360x640)

Screenshots were taken from the app. Soon it will be available on Play Store.


## License

~~~
Copyright 2018 Nikola Šoljaga

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~

