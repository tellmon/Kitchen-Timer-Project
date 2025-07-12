

public class Clock {
    private static final boolean True = true;
    private static final boolean False = false;
    // Attributes
    // declares variables
    public int mySeconds = 0;
    public int myMinutes = 0;
    public int myHours = 0;
    
    public int seconds;
    public int minutes;
    public int hours;
    public boolean done = False;
    
    //methods
    public Clock() { // makes mySeconds = to zero
        mySeconds = 0;
    }
    
    public void increment(int num) { // adds numbers 

        if (num <= 59 && num > -1) { // normal range 0 to 60
        if (mySeconds < 59) {
            mySeconds += num;
            done = True;
        }
        
            
        if (myMinutes < 59 && done == False) {
            mySeconds = 0;
            myMinutes += num;
            done = True;}

        
        if (myHours < 59 && done == False) {
            mySeconds = 0;
            myMinutes = 0;
            myHours += num;
        }}    
        
if (num >= 60) { // Bigger than sixty seconds
    if (myMinutes < 60) {
        myMinutes += num / 60; 
        mySeconds += num % 60; 

        if (mySeconds >= 60) { 
            myMinutes += mySeconds / 60; 
            mySeconds = mySeconds % 60; 
        }
        
        if (myMinutes >= 60) { 
            myHours += myMinutes / 60; 
            myMinutes = myMinutes % 60; 
        }
    } else {
        myHours += num / 3600; 
        myMinutes += (num % 3600) / 60; 
        mySeconds += (num % 3600) % 60; 

        if (mySeconds >= 60) { 
            myMinutes += mySeconds / 60; 
            mySeconds = mySeconds % 60; 
        }

        if (myMinutes >= 60) { 
            myHours += myMinutes / 60; 
            myMinutes = myMinutes % 60; 
        }
    }
}

// smaller than 0 seconds
if (num < 0){        
if (myHours <= 0 && myMinutes <= 0 && mySeconds <= 0){
    myHours = 255;
    myMinutes = 59;
    mySeconds = 59;
}

 mySeconds += num;
 
    if (mySeconds < 0) {
        int secondsToSubtract = -mySeconds; 
        myMinutes -= (secondsToSubtract / 60) + 1; 
        mySeconds = 60 - (secondsToSubtract % 60);
        if (myMinutes < 0) {
            myMinutes = 60 + myMinutes; 
            myHours -= 1; 
            if (myHours < 0) {
                myHours = 255; 
            }
        }
    }
}       
    done = False;
    }
        
                
    
    
    public String getTime() { // makes all two digest
            
    		if (mySeconds < 0) {mySeconds = mySeconds * -1;}
    		if (myMinutes < 0) {myMinutes = myMinutes * -1;}
    		if (myHours < 0) {myHours = myHours * -1;}  		
    		
            if (mySeconds < 10 && myMinutes > 9 && myHours > 9) { //just seconds
                return myHours + ":" + myMinutes + ":0" + mySeconds;
            }
            
            if (mySeconds < 10 && myMinutes < 10 && myHours > 9) { // all but hourse
                return myHours + ":0" + myMinutes + ":0" + mySeconds;
            }
            
            if (mySeconds < 10 && myMinutes < 10 && myHours < 10) {  // all small
                return "0" + myHours + ":0" + myMinutes + ":0" + mySeconds;
            }

            if (mySeconds > 9  && myMinutes > 9 && myHours > 9) { // all large
                return myHours + ":" + myMinutes + ":" + mySeconds;
            }
 
            
            if (mySeconds > 9 && myMinutes < 10 && myHours > 9) { // just minites
                return myHours + ":0" + myMinutes + ":" + mySeconds;
            }
            
            if (mySeconds > 9  && myMinutes < 10 && myHours < 10) {
                return "0" + myHours + ":0" + myMinutes + ":" + mySeconds; // minutes and hourse
            }
            
            if (mySeconds > 9  && myMinutes > 9 && myHours < 10) {
                return "0" + myHours + ":" + myMinutes + ":" + mySeconds; // just hours
            }
            
            if (mySeconds < 10  && myMinutes > 9 && myHours < 10) {
                return "0" + myHours + ":" + myMinutes + ":0" + mySeconds; //  hours + seconds
            }
                
                     
            return hours + ":" + minutes + ":" + seconds; // returns the right value
    }
    
    public void reset(int h, int m, int s) { // resets all values to zero
        mySeconds = s;
        myMinutes = m;
        myHours = h;
    }
    
    public void setTime(int h, int m, int s) {  // gets input to set the values to
        
        mySeconds = s;
        myMinutes = m;
        myHours = h;
    }
    
    public int hours() {return myHours;}
    public int minutes() {return myMinutes;}
    public int seconds() {return mySeconds;}
}
