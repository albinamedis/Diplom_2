package stellarburgers.nomoreparties.orders;

import java.util.List;

public class IngredientsResponse {
    private boolean success;
    private List<Ingredients> data;

    public IngredientsResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Ingredients> getData() {
        return data;
    }

    public void setData(List<Ingredients> data) {
        this.data = data;
    }
}
