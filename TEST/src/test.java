import org.apache.lucene.store.Directory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class test
{
//    private static final String filePath=System.getProperty("user.dir")+"/out.txt";
    public static void main(String [] argv) throws Exception
    {
//        FileSlicer get_html = new FileSlicer("src/test.txt");
//        int start=0;
//        List<String> tmp = get_html.getEachHtml();
//        for(int i=0;i<tmp.size();i++) {
//            if(tmp.get(i).matches("<html>.{0}|<HTML>.{0}"))
//                start = i;
//            else if(tmp.get(i).matches("</html>|</HTML>")) {
//                String temp = get_html.getBlockOfHtml(tmp, start, i);
//                index_deal.addDocument(gettoken_deal.gettoken(temp));
//            }
//        }

        String [] convert={"AND","NOT","OR"};
        File f=new File("src/test.txt");
        Document doc = Jsoup.parse(f,"UTF-8");
        Elements elements = doc.select("html");
        String parse = elements.text();
        String[] parse_list = parse.split(" ");
        List<String> parse_result = new ArrayList<>();

        for(String s:parse_list) {
            if (s.matches("[a-zA-Z]+[']?[a-zA-Z]")) {
                if(Arrays.asList(convert).contains(s))
                    s=s.toLowerCase();
                parse_result.add(s);
            }

        }

        Index in = new Index("indexstorage");
        in.createIndex(parse_result);
        List<String>correct_parse_result=new ArrayList<>();
        for(int i=0;i<parse_result.size();i++) {
            if(!correct_parse_result.contains(parse_result.get(i)))
                correct_parse_result.add(parse_result.get(i));
        }

        Collections.sort( correct_parse_result );

        try {
            for(int i=0;i<correct_parse_result.size();i++) {
                in.search(correct_parse_result.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}