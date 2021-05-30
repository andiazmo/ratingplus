/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package utilidades;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronExpression;

/**
 *
 * @author User
 */
public class CronExpresionHelper {
    
    private String expression;
    private Calendar calendar;
    private Map<String,Integer> dict;
    
    public CronExpresionHelper() {
        loadDicc();
    }
    
    public CronExpresionHelper(String expression) {
        loadDicc();
        this.expression = expression;
    }
    
    public String getExpression() {
        return expression;
    }
    
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public CronExpresionHelper(Calendar calendar) {
        this.calendar = calendar;
    }
    
    public void process(){
        try {
            CronExpression cron = new CronExpression(expression);
            processDate(cron.getExpressionSummary());
        } catch (ParseException ex) {
            Logger.getLogger(CronExpresionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processDate(String summary){
        String propertyDate[] = summary.split("\n");
        calendar = Calendar.getInstance();
        for(String property: propertyDate){
            if(!validateIncorrectProperty(property)){
                PropertyTuple propertyTuple = new PropertyTuple(property, ":");
                addPropertyCalendar(propertyTuple);
            }
        }
    } 

    private void loadDicc(){
        dict = new HashMap<>();
        dict.put("seconds",Calendar.SECOND);
        dict.put("minutes",Calendar.MINUTE);
        dict.put("hours",Calendar.HOUR_OF_DAY);
        dict.put("daysOfMonth",Calendar.DAY_OF_MONTH);
        dict.put("daysOfWeek",Calendar.DAY_OF_WEEK);
        dict.put("months",Calendar.MONTH);
        dict.put("years",Calendar.YEAR);
    }
    
    public boolean compareDate(Date date){
        try {
            CronExpression cron = new CronExpression(expression);
            return cron.isSatisfiedBy(date);
        } catch (ParseException ex) {
            Logger.getLogger(CronExpresionHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Date getDate(){
        return calendar.getTime();
    }

    private boolean validateIncorrectProperty(String property) {
        return property.contains("*") || property.contains("?") 
                || property.contains("false");
    }

    private void addPropertyCalendar(PropertyTuple tuple) {
        if(dict.containsKey(tuple.getKey())){
            Integer keyCalendar = dict.get(tuple.getKey());
            Integer value = Integer.valueOf(tuple.getValue());
            calendar.set(keyCalendar, value);
        }
    }
}
