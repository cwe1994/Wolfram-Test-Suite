package WolframTestSuite;

//This enumerated class covers the different file types that can be created.

public enum TestDocType {
	Notebook, Package, Template, Javascript, Text, XML, CSS, HTML;
	
	private String id;
	
	//id should cover the file extensions
	
	static{
		Notebook.id = "nb";
		Package.id = "wl";
		Template.id = "temp";
		Javascript.id = "js";
		Text.id = "txt";
		XML.id = "xml";
		CSS.id = "css";
		HTML.id = "html";
	}
	public String getId(){
		return id;
	}
}
