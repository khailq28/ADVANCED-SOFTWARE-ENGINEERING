
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author khai
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Date created;
    private int coin;
    private int lv;
    private String giftdate;
    
    public String getGiftdate() {
        return giftdate;
    }

    public void setGiftdate(String giftdate) {
        this.giftdate = giftdate;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getLv() {
        return lv;
    }
    private int exp;

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCoin() {
        return coin;
    }

    public int getExp() {
        return exp;
    }

    public String getDateString(String pattern){ // pattern="dd/MM/yyyy"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getCreated());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDateString(){ // pattern="dd/MM/yyyy"
        return getDateString("dd/MM/yyyy");
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}
