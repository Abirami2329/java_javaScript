package quiz;

import java.io.*;
import java.lang.*;

class Quiz
{
	private InputStreamReader ir=new InputStreamReader(System.in);
	private BufferedReader br;
	private FileOutputStream fos;
	private	PrintStream ps; 
	private FileReader frs;

	private boolean checkFile(String filename)
	{
		File myFile=new File(filename);
		boolean b=myFile.exists();	
		
		return b;
	}

	private boolean createQuestions()
	{
		int go=0,saved=0;
		boolean result=false,ret=false;
		
		String[] ques;
		String msg="",nos="";
		
		try
		{
			br=new BufferedReader(ir);
			while(result==false)
			{
				System.out.print("\n Please mention number of questions to store");
				System.out.print("\n [Enter 0 to skip this step] : ");
				nos=br.readLine();
				
				result=isNum(nos);
				if(result==false)
				{
					System.out.println("The input should be an positive integer only...!");	
				}
				else
				{
					if(nos.equals("0"))
					{
						go=0;	
					}	
					else
					{
						go=1;	
					}
				}
			}
			
			if(go==0)
			{
				ret=true;	
			}
			else
			{
				for(int i=1;i<=Integer.parseInt(nos);i++)
				{
					saved=0;
					ques=new String[6];
					while(saved!=1)
					{
						msg="";
						br=new BufferedReader(ir);
						System.out.println("\n Please type Question Number "+i+" : ");
						ques[0]=br.readLine();
						System.out.println("\n Option 1 : ");
						ques[1]=br.readLine();
						System.out.println("\n Option 2 : ");
						ques[2]=br.readLine();
						System.out.println("\n Option 3 : ");
						ques[3]=br.readLine();
						System.out.println("\n Option 4 : ");
						ques[4]=br.readLine();
						System.out.print("\n Correct Answer(Mention option number only) : ");
						ques[5]=br.readLine();
						if(ques[0].equals(""))
						{
							msg="The question body should not be blank...!\n";	
						}
						for(int j=1;j<=4;j++)
						{
							if(ques[j].equals(""))
							{
								msg=msg+"Option "+j+" should have been mentioned...!\n";	
							}	
						}
						if(isNum(ques[5])==false)
						{
							msg=msg+"The input for correct answer should be an integer...!\n";	
						}
						else
						{
							if(Integer.parseInt(ques[5])>=1 && Integer.parseInt(ques[5])<=4)
							{
							}
							else
							{
								msg=msg+"Invalid answer indication number It must be within 1 and 4...!";	
							}	
						}
						if(!msg.equals(""))
						{
							System.out.print("\n The following mistakes have been occured :-");
							System.out.print("\n --------------------------------------------");
							System.out.print("\n " + msg);
							saved=0;	
						}
						else 
						{
							try
							{
								fos=new FileOutputStream("Questions.txt",true);
								ps=new PrintStream(fos);
								ps.print("\n");
								ps.print(ques[0]+","+ques[1]+","+ques[2]+","+ques[3]+","+ques[4]+","+ques[5]);
								fos.close();
								System.out.println("Successfully saved...");
								saved=1;
							}
							catch(Exception rx)
							{
								System.out.println("Error in accepting details. Please try again...!");
								saved=0;
							}
						}
					}
					ret=true;	
				}	
			}
		}
		catch(Exception ex)
		{
			ret=false;
		}

		return ret;
	}
	private boolean isNum(String input)
	{
		boolean b=false;
		
		char a[]=new char[input.length()];
		a=input.toCharArray();
		
		for(int i=0;i<a.length;i++)
		{
		  if(a[i]>='0' && a[i]<='9')
		  {
		    b=true;	  
		  }	
		  else
		  {
		    b=false;
		    break;	  
		  }
		}
		
		return b;
	}	
	private int startQuiz()
	{
		int retVal=0,counter=0,commit=0,nos=0;
		int attempted=0,skipped=0,correct=0,wrong=0;
		boolean made=false;
		
		String thisLine,summary="";
		String[] fullText;
		String[] question;
		
		if(checkFile("Questions.txt")==false)
		{
			retVal=1;	
		}
		else
		{
			try
			{
				frs=new FileReader("Questions.txt");
				br=new BufferedReader(frs);
				while((thisLine=br.readLine()) != null)
				{
					counter+=1;
					nos+=1;
				}
				
				frs.close();
				
				if(counter==0)
				{
					retVal=2;	
				}
				else
				{
					frs=new FileReader("Questions.txt");
					br=new BufferedReader(frs);
					fullText=new String[counter];
					counter=0;
					while((thisLine=br.readLine()) != null)
					{
						fullText[counter]=thisLine;
						counter+=1;
					}
					frs.close();
					
					System.out.println("Number of questions "+fullText.length);
					for(int i=0;i<fullText.length;i++)
					{
						question=new String[fullText.length];
						question=fullText[i].split(",");
						commit=0;
						made=false;
						
						br=new BufferedReader(ir);
						
						while(commit!=1)
						{
							try
							{
								System.out.print("\n Question Number "+(i+1));
								System.out.print("\n ------------------");
								System.out.print("\n "+question[0]);
								System.out.print("\n Option 1 -->"+question[1]);
								System.out.print("\n Option 2 -->"+question[2]);
								System.out.print("\n Option 3 -->"+question[3]);
								System.out.print("\n Option 4 -->"+question[4]);
								while(made!=true)			
								{
									System.out.print("\n YOUR ANSWER[1-4] (Type 0 to Skip) : ");
									String opt=br.readLine();
									if(isNum(opt)==false)
									{
										System.out.println("Invalid input. Your choice should be an integer only...!");
										made=false;	
									}
									else
									{
										
										if(Integer.parseInt(opt)>=1 && Integer.parseInt(opt)<=4)
										{
											attempted+=1;
											if(Integer.parseInt(opt)==Integer.parseInt(question[5]))
											{
												correct+=1;	
											}
											else
											{
												if(summary.equals(""))
												{
													summary="Question : "+question[0]+"\n";
													summary=summary + "Correct Answer : "+question[5]+" "+question[Integer.parseInt(question[5])]+"\n";
													summary=summary + "Your Answer was : "+opt+" "+question[Integer.parseInt(opt)];
												}
												else
												{
													summary=summary + "\n\n Question : "+question[0]+"\n";
													summary=summary + "Correct Answer : "+question[5]+" "+question[Integer.parseInt(question[5])]+"\n";
													summary=summary + "Your Answer was : "+opt+" "+question[Integer.parseInt(opt)];
														
												}
												
												wrong+=1;
											}
											made=true;
										}
										else
										{
											if(Integer.parseInt(opt)==0)
											{
												skipped+=1;
												made=true;
											}
											else
											{
												System.out.println("Incorrect choice. Please make it between 1 and 4 only...!");
												made=false;	
											}	
										}
									}
								}
							}
							catch(Exception ex)
							{
								System.out.println("Incorrect"+ex);
								ex.printStackTrace();
								retVal=3;	
							}
							commit=1;
						}
					}
					
					if(retVal==0)
					{
						System.out.println("\n CONTEST RESULT");
						System.out.println("\n ------------------------------");
						System.out.println("\n Number of Question(s) Presented = "+nos);
						System.out.println("\n Number of Question(s) Attempted = "+attempted);
						System.out.println("\n Number of Question(s) Skipped = "+skipped); 		
						System.out.println("\n Correct Answers = "+correct);
						System.out.println("\n Wrong Answers = "+wrong);
						if(attempted!=0)
						{
							System.out.println("\n Accuracy Percentage = "+(correct*100)/attempted+"%\n");
						}
						else
						{
							System.out.println("\n Accuracy Percentage = Not Calculated\n");	
						}
						
						if(!summary.equals(""))
						{
							System.out.println("\n\n Correct Answer(s) of Wrong answered question(s) :-\n");
							System.out.print("------ //exit the Quiz--------------------------------------------------\n");
							System.out.print(summary+"\n");	
						}
						System.out.print("--------------------------------------------------------\n\n");
					}
				}
			}
			catch(Exception mx)
			{
				retVal=3;	
			}
		}
		
		return retVal;
	}
	
	public static void main(String args[])
	{
		Quiz z = new Quiz();
		
		int ch=0,allow=0;
		boolean go=false;
		String uname="",passwd="",msg="";
		
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		
		while(ch!=3)
		{
			try
			{
				System.out.print("\n QUIZ APPLICATION");
				System.out.print("\n ----------------");	
				System.out.print("\n 1. Create Questions");
				System.out.print("\n 2. Start Quiz Contest");
				System.out.print("\n 3. Exit Application");
				System.out.print("\n Please enter a choice[1-3]:");
				
				ch=Integer.parseInt(br.readLine());
				if(ch>=1 && ch<=3)
				{
					switch(ch)
					{
						case 1:
							if(z.checkFile("Questions.txt")==true)
							{
								System.out.print("\n The Master File is already exists.");
								System.out.print("\n Do you wish to alter it[y/n]:");
								String s=br.readLine();
								
								if(s.equals("y") || s.equals("Y"))
								{
									go=true;	
								}
								else
								{
									go=false;	
								}
							}
							else
							{
								go=true;	
							}
							
							if(go==true)
							{
								allow=0;
								int t=1;
								
								while(allow==0 && t<=3)
								{
									System.out.print("\n Mention UserName :");
									uname=br.readLine();
									System.out.print("\n Mention Password :");
									passwd=br.readLine();
									
									if(uname.equals("admin") && passwd.equals("admin"))
									{
										allow=1;										
									}
									else
									{
										if(t<3)
										{
											System.out.println("Incorrect username or password. Please try again...!");
											System.out.println("Attempt = "+t+"/3");
											allow=0;	
											t+=1;
										}
										else
										{
											System.out.println("Sorry, your attempt is over. System will now back to opt prompt...");
											allow=2;	
										}
									}
								}
								
								if(allow==1)
								{
									boolean res=z.createQuestions();
									if(res==false)
									{
										System.out.println("Unable to continue execution. Please try again...!");	
									}
								}
								else
								{
									ch=0;	
								}
							}
							else
							{
								ch=0;	
							}
							break;		
						case 2:
							int r=z.startQuiz();
							switch(r)
							{
								case 0:
									msg="";
									break;
								case 1:
									msg="\nUnable to start the quiz. Could not locate the master file...!\n";
									msg=msg+"Please make sure the file \"Questions.txt\" exists in the ";
									msg=msg+"application path.\n";
									msg=msg+"If you are unable to locate the file, please hit OPTION 1 and ";
									msg=msg+"create a new file.";
									break;
								case 2:
									msg="\nUnable to start the quiz. Could not locate entries in the master file...!\n";
									msg=msg+"Please make sure the master file is not empty and it is valid in ";
									msg=msg+"the application path. Otherwise hit OPTION 1 and create a new file.";
									break;	
								case 3:
									msg="Sorry, the program is interrupted due to in-process exception...!";
									break;	
							}
							
							if(!msg.equals(""))
							{
								System.out.println(msg);	
							}
							break;
						case 3:
							System.out.println("Thank you for attending Quiz...");
							System.out.println("Goodbye...");	
					}
				}
				else
				{
					System.out.println("Invalid option. Please try again...");
					ch=0;	
				}
			}	
			catch(Exception ex)
			{
				System.out.println("Error in accepting input...!");
				ch=0;	
			}		
		}
	}
}