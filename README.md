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
