import javax.swing.JLabel;

public class AI {
	
	
	 static void compplay(int n)
	    {
	        boolean carry=true;
	        if(XOButton.type==3)
	            carry=winorstop(XOButton.a,XOButton.b,n);
	        if((XOButton.type==2||XOButton.type==3) && carry)
	        {
	            if(n==1)
	                carry=winorstop(XOButton.c,XOButton.d, 4);
	            else
	                carry=winorstop(XOButton.c,XOButton.d,1);
	        }
	        if(carry)
	                doany();
	    }
	 
	 static boolean winorstop(int l,int m,int n)
	    {
	        if(LogicalArray.GArray[l][0]+LogicalArray.GArray[l][1]+LogicalArray.GArray[l][2] == n*2)
	        {
	        	for(int i=0;i<3;i++)
	            {
	                if(XOButton.play(l,i))
	                    return false;
	            }
	        }
	        else
	        	if(LogicalArray.GArray[0][m]+LogicalArray.GArray[1][m]+LogicalArray.GArray[2][m]==n*2)
	            {
	        		for(int i=0;i<3;i++)
	                {
	                    if(XOButton.play(i,m))
	                        return false;
	                }
	            }
	            else
	            	if(LogicalArray.GArray[0][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[2][2]==n*2)
	                {
	            		 for(int i=0;i<3;i++)
	                        {
	                                if(XOButton.play(i,i))
	                                        return false;
	                        }
	                }
	                else
	                	 if(LogicalArray.GArray[2][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[0][2]==n*2)
	                     {
	                             for(int i=0,j=2;i<3;i++,j--)
	                             {
	                                     if(XOButton.play(i,j))
	                                             return false;
	                             }
	                     }

	        return true;
	    }
	 
	 static  void doany()
	    {
	        int l=2,m=0;
	        switch(XOButton.cnt)
	        {
	            case 0: XOButton.play(0,0);
	                    break;
	            case 1: if(!(XOButton.play(1,1)))
	            	XOButton.play(0,0);
	                    break;
	            case 2: if(!(XOButton.play(2,2)))
	            	XOButton.play(0,2);
	                    break;
	            case 3: if((LogicalArray.GArray[0][1]+LogicalArray.GArray[1][1]+LogicalArray.GArray[2][1])==XOButton.val)
	            	XOButton.play(0,1);
	                    else
	                        if((LogicalArray.GArray[1][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[1][2])==XOButton.val)
	                        	XOButton.play(1,0);
	                        else
	                            if(LogicalArray.GArray[0][1]!=0)
	                            	XOButton.play(0,2);
	                            else
	                            	XOButton.play(2,0);

	                    break;
	            default : while(!(XOButton.play(l,m)))
	                      {
	                        l=XOButton.rnd.nextInt(3);
	                        m=XOButton.rnd.nextInt(3);
	                      }
	                    break;
	        }
	    }
}
