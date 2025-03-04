package lbj.king.proyecto.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Userr {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    private String name;
    private String password;
    private float currency;

    @Lob
    private Blob image;

    @OneToMany(mappedBy = "owner")
    private List<Prize> prizeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Play> playList;

    protected Userr() {}

    public Userr(String name,String psw){
        this.name=name;
        this.password=psw;
        this.currency=0;
        this.playList=new ArrayList<Play>();
        this.prizeList=new ArrayList<Prize>();
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String psw){
        this.password=psw;
    }
    public void setCurrency(float cv){
        this.currency=cv;
    }

    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public float getCurrency(){
        return this.currency;
    }
    public List<Play> getLista(){
        return this.playList;
    }
    public List<Prize> getPremios(){
        return this.prizeList;
    }
    public long getId(){
        return this.id;
    }

    public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}

    public void addPlay(Play p){
        this.playList.add(p);
    }
    public void addPrize(Prize p){
        this.prizeList.add(p);
    }

}
