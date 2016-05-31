import javax.swing.JLabel;

public class AI {
	int[][] pos=new int[3][3];
	
	
	 static void compplay(int n)
	    {
	        boolean carry=true;
	        if(XOButton.type==3)
	            carry=winorstop(XOButton.a,XOButton.b,n);
	        if((XOButton.type==2||XOButton.type==3) && carry)
	        {
	            if(n==1)
	                carry=winorstop(XOButton.c,XOButton.d,4);
	            else
	                carry=winorstop(XOButton.c,XOButton.d,1);
	        }
	        if(carry)
	                doany();
	    }
	 
	 static boolean winorstop(int l,int m,int n)
	    {
	        if(((LogicalArray.GArray[0] + LogicalArray.GArray[1] + LogicalArray.GArray[2]) == n*2)
			|| ((LogicalArray.GArray[3] + LogicalArray.GArray[4] + LogicalArray.GArray[5]) == n*2)
			|| ((LogicalArray.GArray[6] + LogicalArray.GArray[7] + LogicalArray.GArray[8]) == n*2))
	        {
	            for(int i=0;i<9;i++)
	            {
	                if(XOButton.play(i))
	                    return false;
	            }
	        }
	        else
	        	if(((LogicalArray.GArray[0] + LogicalArray.GArray[3] + LogicalArray.GArray[6]) == n*2)
	        	|| ((LogicalArray.GArray[1] + LogicalArray.GArray[4] + LogicalArray.GArray[7]) == n*2)
	        	|| ((LogicalArray.GArray[2] + LogicalArray.GArray[5] + LogicalArray.GArray[8]) == n*2))
	            {
	                for(int i=0;i<9;i++)
	                {
	                    if(XOButton.play(i))
	                        return false;
	                }
	            }
	            else
	            	if(((LogicalArray.GArray[0] + LogicalArray.GArray[4] + LogicalArray.GArray[8]) == n*2))
	                {
	                        for(int i=0;i<9;i=i+4)
	                        {
	                                if(XOButton.play(i))
	                                        return false;
	                        }
	                }
	                else
	                    if(((LogicalArray.GArray[6] + LogicalArray.GArray[4] + LogicalArray.GArray[2]) == n*2))
	                    {
	                            for(int j=2;j<8;j+=2)
	                            {
	                                    if(XOButton.play(j))
	                                            return false;
	                            }
	                    }

	        return true;
	    }
	 
	 static void doany()
	    {
	        int l=2,m=0;
	        switch(XOButton.cnt)
	        {
	            case 0: XOButton.play(0);
	                    break;
	            case 1: if(!(XOButton.play(4)))
	                        XOButton.play(0);
	                    break;
	            case 2: if(!(XOButton.play(8)))
	                        XOButton.play(2);
	                    break;
	            case 3: if((LogicalArray.GArray[0] + LogicalArray.GArray[4] + LogicalArray.GArray[8])==XOButton.val)
	                        XOButton.play(1);
	                    else
	                        if((LogicalArray.GArray[3] + LogicalArray.GArray[4] + LogicalArray.GArray[5])==XOButton.val)
	                            XOButton.play(3);
	                        else
	                            if(LogicalArray.GArray[1]!=0)
	                                XOButton.play(2);
	                            else
	                                XOButton.play(6);

	                    break;
	            default : while(!(XOButton.play(l)))
	                      {
	                        l=XOButton.rnd.nextInt(9);
	                      }
	                    break;
	        }
	    }
}
