# REST API Client

Cast JSON nativa, ejemplo.

```java
private City getCity(){
    String cityJson = "{\"id\":\"100\",\"name\":\"Mexico City\"}";

    City city = null;

    //Native form JSON cast. Example
    try{
        JSONObject jsonObject = new JSONObject(cityJson);
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");

        city = new City(id,name);
    }catch (JSONException je){
        je.printStackTrace();
    }

    if(city == null){
        city = new City();
    }
    
    return city;
}
```

Intalación de **GSON** en gradle

```gradle
implementation 'com.google.code.gson:gson:2.8.5'
```

Cast con GSON.

```java
private City getCity(){
    String cityJson = "{\"id\":\"100\",\"name\":\"Mexico City\"}";

    City city = null;

    Gson gson = new Gson();
    city = gson.fromJson(cityJson,City.class);

    return city;
}
```

Para omitir campos en el cast debemos exponer las propiedades que si con la 
anotación **@Expose** y al crear nuestro objeto Gson especificarlo.

```java
public class City {

    @Expose
    public int id;

    @Expose
    public String name;

    ...
```

```java
Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
```

Leyendo un recurso de la carpeta **raw**.

> **/res/raw/country.json**

```json
{
  "id": 100,
  "name": "Mexico",
  "cities": [
    {
      "id": 101,
      "name": "Mexico City"
    },
    {
      "id": 101,
      "name": "Guadalajara"
    },
    {
      "id": 103,
      "name": "Monterrey"
    }
  ]
}
```

```java
private String readFile(){

    String jsonString = "";

    try {

        InputStream inputStream = this.getResources().openRawResource(R.raw.country);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        jsonString = sb.toString();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return jsonString;
}
```

Para realizar el cat con un nombre diferente al de la propiedad, deberiamos utilizar la anotación **@SerializedName("PropertyName")**

Crear **BuildProperties** en Gradle:

```gradle
buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        buildConfigField "String", "ENDPOINT", "\"https://samples.openweathermap.org/data/2.5/\""
        buildConfigField "String", "API_KEY", "\"a66b6cf14ded881004b811bc7a7b4d63\""
    }
    debug {
        buildConfigField "String", "ENDPOINT", "\"https://samples.openweathermap.org/data/2.5/\""
        buildConfigField "String", "API_KEY", "\"a66b6cf14ded881004b811bc7a7b4d63\""
    }
}
```

Weather Contract

```java
public interface IWeatherService {

    //weather?q={city}&appid={api_key}
    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String api_key);
}
```

Obtener datos

```java
Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

Retrofit retrofit = new Retrofit
        .Builder()
        .baseUrl(BuildConfig.ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

IWeatherService weatherService = retrofit.create(IWeatherService.class);
Call<City> cityCall = weatherService.getCity(CITY_NAME,BuildConfig.API_KEY);
cityCall.enqueue(new Callback<City>() {
    @Override
    public void onResponse(Call<City> call, Response<City> response) {
        City city = response.body();
        Toast.makeText(MainActivity.this,city.getName(),Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onFailure(Call<City> call, Throwable throwable) {
        Toast.makeText(MainActivity.this,R.string.error_service,Toast.LENGTH_SHORT)
                .show();
    }
});
```


