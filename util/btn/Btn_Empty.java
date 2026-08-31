package util.btn;

public class Btn_Empty extends MainBtn {
    public Btn_Empty(String text) {
        super(text);
    }

    public Btn_Empty(String text , int Rounded){
        this(text);
        super.setRounded(Rounded);
    }

    public Btn_Empty(String text , int Rounded , boolean isShadow , int shadowSize ){
        this(text);
        super.setRounded(Rounded);
        super.setShadow(isShadow, shadowSize);
    }
    
}
