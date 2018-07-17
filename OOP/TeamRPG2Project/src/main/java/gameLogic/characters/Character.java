package gameLogic.characters;

import gameLogic.attributes.Status;
import gameLogic.buffs.Buff;
import gameLogic.skills.Skill;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Character {
    private HashMap<String, Status> status = new HashMap<>();
    private HashMap<String, Skill> skills = new HashMap<>();
    private List<Buff> activeBuffs = new ArrayList<>();

    public void addBuff(Buff addedBuff) {
        addedBuff.onApplied();
        if (!addedBuff.isExpired()) {
            activeBuffs.add(addedBuff);
        }
    }

    public List<Buff> getActiveBuffs() {
        return activeBuffs;
    }

    public void cleanBuffList(){
         activeBuffs.removeIf((Buff buff)->{
             if (buff.isExpired()) {
               buff.onRemoved();
               return true;
           } else return false;
        });
    }

    public void changeStatus(String statusName, double amount){
        status.get(statusName).changeValueBy(amount);
    }

    //This method can be simply described in Skills
    public void eventChangeStatus(String statusName, double amount, String methodName){
        changeStatus(statusName, amount);
        applyBuffs(methodName);
    }

    //This method can be simply described in Skills
    public void eventSharedChangeStatus(String statusName, double amount, String userMethodName, Character oponent, String oponentMethodName){
        changeStatus(statusName, amount);
        applyBuffs(userMethodName);
        oponent.applyBuffs(oponentMethodName);
    }

    public void applyBuffs(String usingSpecificMethod){
        String methodName = usingSpecificMethod;
        activeBuffs.forEach((Buff buff)->{
            try {
                Method method = buff.getClass().getMethod(methodName);
                method.invoke(buff);
            } catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { throw new RuntimeException(e);
            }
        });
    }
}
