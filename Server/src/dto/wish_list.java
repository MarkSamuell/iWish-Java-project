package dto;

/**
 *
 * @author Salma Ahmed
 */
public class wish_list {
    

    private String user_email;
    private String item_name;
    private int current_contribution;
    private int item_id;
    private int item_price;
 
    public String user_email() {
        return user_email;
    }

    public void user_email(String user_email) {
        this.user_email = user_email;
    }
    public String item_name() {
        return item_name;
    }

    public void item_name(String item_name) {
        this.item_name = item_name;
    }
    public int current_contribution() {
        return current_contribution;
    }

    public void current_contribution(int current_contribution) {
        this.current_contribution = current_contribution;
    }

    public int item_id() {
        return item_id;
    }

    public void item_id(int item_id) {
        this.item_id = item_id;
    }
     public int item_price() {
        return item_price;
    }

    public void item_price(int item_price) {
        this.item_price = item_price;
    }

  
   
    public wish_list(String user_email,  int item_id,String item_name,int item_price,int current_contribution) {
    
        this.user_email = user_email;
        this.current_contribution = current_contribution;
        this.item_id = item_id;
        this.item_price=item_price;
        this.item_name=item_name;
    }
}