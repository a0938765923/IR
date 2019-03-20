import org.apache.lucene.store.Directory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class test
{
    private static final String filePath=System.getProperty("user.dir")+"/out.txt";
    public static void main(String [] argv) throws IOException
    {
        System.out.println(filePath);
        Directory index_dir;
        File f=new File("src/test.txt");

        Document doc = Jsoup.parse(f,"utf-8");
        Elements elements = doc.select("html");
        String parse = elements.text();
        String[] parse_list = parse.split(" ");
        List<String> parse_result = new ArrayList<>();

        for(String s:parse_list)
        {
            if (s.matches("[a-zA-Z]+[']?[a-zA-Z]"))
            {
                parse_result.add(s);
                System.out.println(s);
            }

        }
        Indexer in = new Indexer(filePath);
        in.test(parse_result);


    }
}