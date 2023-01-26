import java.io.IOException;
import java.net.URI;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    


    public String handleRequest(URI url)
    {
        int count = 0;

        String[] searchList = new String[100];
        String[] resultList = new String[100]; 

        System.out.println("Path: "+url.getPath());
        if(url.getPath().contains("/add"))
        {
            String[] parameters = url.getQuery().split("="); 
            searchList[count] = parameters[1]; 
            count++; 
            
        }
        if(url.getPath().contains("/search"))
        {
            int j = 0;
            String[] parameters = url.getQuery().split("="); 
            for(int i = 0; i<searchList.length; i++)
            {
                if(searchList[i].contains(parameters[1])){
                    resultList[j] = searchList[i];
                    j++; 
                }
            }
            String results = "";
            for(int i = 0; i<resultList.length; i++)
            {
                if(resultList[i]!=null)
                {
                    results+=resultList[i]; 
                    results += " "; 
                }
            }
            return results; 
        }
        return "";

    }
    
}

class SearchEngine
{

public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}