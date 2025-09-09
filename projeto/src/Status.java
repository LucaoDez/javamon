public class Status{
    private int hpMAX, hpATUAL, def, atk, speed;
    
    public Status(int hpMAX, int hpATUAL, int def, int atk, int speed){
        this.hpMAX = hpMAX;
        this.hpATUAL = hpATUAL;
        this.def = def;
        this.atk = atk;
        this.speed = speed;
    }

    public int getHpMax() {
        return hpMAX;
    }

    public int getHpATUAL() {
        return hpATUAL;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpeed() {
        return speed;
    }
}