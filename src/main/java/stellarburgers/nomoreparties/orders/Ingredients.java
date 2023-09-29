package stellarburgers.nomoreparties.orders;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Ingredients {
    private String _id;
    private String name;
    private String type;
    private int proteins;
    private int fat;
    private int carbohydrates;
    private int calories;
    private int price;
    private String image;
    private String image_mobile;
    private String image_large;
    private int __v;


    public Ingredients() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public void setImage_mobile(String image_mobile) {
        this.image_mobile = image_mobile;
    }

    public String getImage_large() {
        return image_large;
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public List<String> createListIngredients(String jsonString){
        Gson gson = new Gson();
        IngredientsResponse ingredientsResponse = gson.fromJson(jsonString, IngredientsResponse.class);

/*        if (ingredientsResponse.isSuccess()) {
            List<Ingredients> ingredientsList = ingredientsResponse.getData();
            for (Ingredients ingredient : ingredientsList) {
                System.out.println("ID: " + ingredient.get_id());
            }
            // Ваши дальнейшие действия с ингредиентами...
        } else {
            System.out.println("Не удалось получить ингредиенты.");
        }*/

        List<Ingredients> ingredientsList = ingredientsResponse.getData();
        // Создание нового списка для хранения значений id
        List<String> idList = new ArrayList<>();
        // Пройдитесь по каждому объекту Ingredients и добавьте его id в idList
        for (Ingredients ingredient : ingredientsList) {
            String id = ingredient.get_id();
            idList.add(id);
        }

       /* for (int i = 0; i < idList.size(); i++) { // i строго меньше размера списка
            System.out.println("_id " + i + ": " + idList.get(i));
            // напечатали траты и их индексы в списке
        }*/
        return idList;
    }
}
