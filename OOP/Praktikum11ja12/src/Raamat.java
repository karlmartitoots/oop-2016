public class Raamat extends Teos{

    private String kirjeldus;
    private String tähis;
    private String laenutaja;
    private int päevadeArv;

    public Raamat(String initKirjeldus, String initTähis, String initLaenutaja, int initPäevadeArv, String kirjeldus, String tähis, String laenutaja, int päevadeArv) {
        super(initKirjeldus, initTähis, initLaenutaja, initPäevadeArv);
        this.kirjeldus = kirjeldus;
        this.tähis = tähis;
        this.laenutaja = laenutaja;
        this.päevadeArv = päevadeArv;
    }

    @Override
    public boolean kasHoidlast() {
        if(this.tähis == "kollane" || this.tähis == "sinine"){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return super.toString() + "\nTegemist on raamatuga.";

    }

}
