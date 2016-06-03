import javax.swing.JLabel;

public class AI {
	
	
	 static void computerPlayer(int n)
	    {
	        boolean placed=true;
	        if(XOButton.type==3)
	        	placed=computerPlayerMove(XOButton.a,XOButton.b,n);
	        if((XOButton.type==2||XOButton.type==3) && placed)
	        {
	            if(n==1)
	            	placed=computerPlayerMove(XOButton.c,XOButton.d, 4);
	            else
	            	placed=computerPlayerMove(XOButton.c,XOButton.d,1);
	        }
	        if(placed)
	                RandomMove();
	    }
	 
	 static boolean computerPlayerMove(int x,int y,int n)
	    {
	        if(LogicalArray.GArray[x][0]+LogicalArray.GArray[x][1]+LogicalArray.GArray[x][2] == n*2)
	        {
	        	for(int i=0;i<3;i++)
	            {
	                if(XOButton.playPlayerVsComputer(x,i))
	                    return false;
	            }
	        }
	        else
	        	if(LogicalArray.GArray[0][y]+LogicalArray.GArray[1][y]+LogicalArray.GArray[2][y]==n*2)
	            {
	        		for(int i=0;i<3;i++)
	                {
	                    if(XOButton.playPlayerVsComputer(i,y))
	                        return false;
	                }
	            }
	            else
	            	if(LogicalArray.GArray[0][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[2][2]==n*2)
	                {
	            		 for(int i=0;i<3;i++)
	                        {
	                                if(XOButton.playPlayerVsComputer(i,i))
	                                        return false;
	                        }
	                }
	                else
	                	 if(LogicalArray.GArray[2][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[0][2]==n*2)
	                     {
	                             for(int i=0,j=2;i<3;i++,j--)
	                             {
	                                     if(XOButton.playPlayerVsComputer(i,j))
	                                             return false;
	                             }
	                     }

	        return true;
	    }
	 
	 static  void RandomMove()
	    {
	        int l=2,m=0;
	        switch(XOButton.cnt)
	        {
	            case 0: XOButton.playPlayerVsComputer(0,0);
	                    break;
	            case 1: if(!(XOButton.playPlayerVsComputer(1,1)))
	            	XOButton.playPlayerVsComputer(0,0);
	                    break;
	            case 2: if(!(XOButton.playPlayerVsComputer(2,2)))
	            	XOButton.playPlayerVsComputer(0,2);
	                    break;
	            case 3: if((LogicalArray.GArray[0][1]+LogicalArray.GArray[1][1]+LogicalArray.GArray[2][1])==XOButton.val)
	            	XOButton.playPlayerVsComputer(0,1);
	                    else
	                        if((LogicalArray.GArray[1][0]+LogicalArray.GArray[1][1]+LogicalArray.GArray[1][2])==XOButton.val)
	                        	XOButton.playPlayerVsComputer(1,0);
	                        else
	                            if(LogicalArray.GArray[0][1] != 0)
	                            	XOButton.playPlayerVsComputer(0,2);
	                            else
	                            	XOButton.playPlayerVsComputer(2,0);

	                    break;
	            default : while(!(XOButton.playPlayerVsComputer(l,m)))
	                      {
	                        l=XOButton.rnd.nextInt(3);
	                        m=XOButton.rnd.nextInt(3);
	                      }
	                    break;
	        }
	    }
}
