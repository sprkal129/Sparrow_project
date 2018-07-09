package xml;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//// commit test comment

public class Xml_main {
	private String input_funcname;
	private String input_func;
	private ArrayList <Scaffold> Scaffold_List = new ArrayList<Scaffold>();
	private String output_filename = "practice.xml";
	private String container_type;

	public void setInput(String[] input){

		this.input_funcname=input[0];
		this.input_func=input[1];
	};
	public void setOutput_filename(String s){this.output_filename=s;}
	public String getOutput_filename() {return output_filename;};
	public void init(){ //Parsing�� HTML�� Scaffold ��ü ���� �� ���� �Է�

		boolean isMember;
		System.out.println("funcname is : " + input_funcname);
		if(input_funcname.matches("std::.+::.+")){
			isMember=true;
			Pattern memFuncPattern = Pattern.compile( "(.+)::(.+)::(.+)");   // the pattern to search for
			Matcher memFuncMatcher = memFuncPattern.matcher(input_funcname);

			if(memFuncMatcher.find()) this.container_type=memFuncMatcher.group(2);
		} else isMember=false;
		String[] splitted=input_func.split("(\ntemplate.+\\> )|(\n)|(template.+\\>)"); //���� https://en.cppreference.com/w/cpp/container/list/remove �� �׽�Ʈ

		//int sl_count=0;
		
			for(int i=0; i<splitted.length; i++){
				Scaffold_List.add(new Scaffold());

				Scaffold_List.get(i).set_isMemberFn(isMember);
				if(isMember) Scaffold_List.get(i).add_arguments(container_type);
				System.out.println("\nsplitted[" + i +"] is : " + splitted[i] + "\n--------------------------------------------------------------");

				String noArgsRegex="([^(]+\\s)?(\\S+)(\\s)(\\S+)\\(\\)(.*?);";
				if(!splitted[i].matches(noArgsRegex)) { //if function has 1<= arguments
					Pattern rowPattern = Pattern.compile("(\\S+\\s)?(\\S+)(\\s)(\\S+?)(\\(\\s)(.*?\\s\\))(.+)?");
					Matcher rowMatcher = rowPattern.matcher(splitted[i]);
					String parameters = "";

					if (rowMatcher.find()) {
						Scaffold_List.get(i).set_return_type(rowMatcher.group(2));
						Scaffold_List.get(i).set_fn_name(rowMatcher.group(4));
						parameters = rowMatcher.group(6);

						System.out.println("return_type : " + Scaffold_List.get(i).get_return_type());
						System.out.println("function_name : " + Scaffold_List.get(i).get_fn_name());
						System.out.println("parameters : " + parameters);
					}

					String paramRegex = "(\\S+\\s)??(\\S+)(\\s)(\\S+)(\\s??)(=.+)??(,\\s|\\s\\))";
					Pattern paramPattern = Pattern.compile(paramRegex);
					Matcher paramMatcher = paramPattern.matcher(parameters);

					int matchCount = 0;

					while (paramMatcher.find()) {
						matchCount++;

						Scaffold_List.get(i).add_arguments(paramMatcher.group(2));
						System.out.println("-------Argument Type is : " + paramMatcher.group(2));
					}
				} else {
					Pattern noArgsPattern = Pattern.compile(noArgsRegex);
					Matcher noArgsMatcher = noArgsPattern.matcher(splitted[i]);


					if (noArgsMatcher.find()) {
						System.out.println("no-arg function found!");
						Scaffold_List.get(i).set_return_type(noArgsMatcher.group(2));
						Scaffold_List.get(i).set_fn_name(noArgsMatcher.group(4));

						System.out.println("return_type : " + Scaffold_List.get(i).get_return_type());
						System.out.println("function_name : " + Scaffold_List.get(i).get_fn_name());
						System.out.println("parameters : (none)" );
					}

				}

		}
}
    /**
     * list�� ����ִ� ���������� xml������ ����� �޼ҵ�
     */
 	public void write() { //Scaffold ��ü�鿡 �����ϴ� xml element�� ����� ���� Scaffold_Element ��ü ����
        
        Document doc = new Document();
        ArrayList<Scaffold_Element> Scaffold_Elements = new ArrayList<Scaffold_Element>();
   
        Element root = new Element("scaffolds"); //��Ʈ ������Ʈ ����
        doc.setRootElement(root);
        
        for(int i =0; i<Scaffold_List.size(); i++){


        	Scaffold_Elements.add(new Scaffold_Element());


        	//scaffold type
			Scaffold_Elements.get(i).SC.setAttribute("type", Scaffold_List.get(i).get_isMemberFn()? "1" : "0");


        	int args_size = Scaffold_List.get(i).get_args_size();

        	for(int j=0; j<args_size; j++)
             	Scaffold_Elements.get(i).Argument_list.add(new Element("arg_type"));

            Scaffold_Elements.get(i).ret_type.setText(Scaffold_List.get(i).get_return_type());
            Scaffold_Elements.get(i).func_name.setText(Scaffold_List.get(i).get_fn_name());

            root.addContent(Scaffold_Elements.get(i).SC);
            Scaffold_Elements.get(i).SC.addContent(Scaffold_Elements.get(i).func_name);
            Scaffold_Elements.get(i).SC.addContent(Scaffold_Elements.get(i).ret_type);
            Scaffold_Elements.get(i).SC.addContent(Scaffold_Elements.get(i).args);


            for(int count=0; count<args_size; count++){

        		Scaffold_Elements.get(i).Argument_list.get(count).setText(Scaffold_List.get(i).get_arguments(count));
        		Scaffold_Elements.get(i).args.addContent(Scaffold_Elements.get(i).Argument_list.get(count));
        }

			Scaffold_Elements.get(i).SC.addContent(Scaffold_Elements.get(i).body);
			Scaffold_Elements.get(i).body.addContent(Scaffold_Elements.get(i).func);
			Scaffold_Elements.get(i).func.addContent(Scaffold_Elements.get(i).func_args);
			Scaffold_Elements.get(i).func_args.addContent(Scaffold_Elements.get(i).func_arg);


            
        }
        XMLOutputter xout = new XMLOutputter();
        Format fo = xout.getFormat();
      //  fo.setEncoding("UTF-8"); //�ѱ����ڵ�
       fo.setIndent(" ");//�鿩����
       fo.setLineSeparator("\r\n");//�ٹٲ�
       fo.setTextMode(Format.TextMode.TRIM);


        try {            
            xout.setFormat(fo);
            xout.output(doc, new FileWriter(this.getOutput_filename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
 	}

	public static void main(String[] args) {

		Xml_main xml = new Xml_main();
		xml.setInput(Scraper.scrap());
		xml.init();

		try {
			xml.write();

		} catch (Exception ee){
			System.out.println("Error - Write Unsuccessful");
		}
	}


}
