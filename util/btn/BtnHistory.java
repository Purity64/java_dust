package util.btn;


public class BtnHistory extends MainBtn {
    private int index;

    public BtnHistory(String text) {
        super(text);
    }

    public BtnHistory(String text , int Rounded){
        this(text);
        super.setRounded(Rounded);
    }

    public BtnHistory(String text , int Rounded , boolean isShadow , int shadowSize ){
        this(text);
        super.setRounded(Rounded);
        super.setShadow(isShadow, shadowSize);
    }

    public void SetIndex(int index){
        this.index = index;
    }
    
    public int GetIndex(){
        return this.index;
    }




    
}
