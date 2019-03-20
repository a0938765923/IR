//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.search.Hits;
//import org.apache.lucene.search.IndexSearcher;
//import org.jsoup.select.Evaluator;
//import org.jsoup.select.QueryParser;
//
//import java.io.IOException;
//import java.text.ParseException;
//
//public class Searching {
//    Evaluator q;
//
//    IndexSearcher is;
//    private static final String filePath=System.getProperty("user.dir")+"/out.txt";
//    public Searching(String path, String q) throws ParseException, IOException {
//        is = new IndexSearcher(path);
////        this.q = QueryParser.parse(q, "contents", new StandardAnalyzer());
//    }
//
////    void show() throws IOException {
////        Hits hits = is.search(q);
////        for (int i = 0; i < hits.length(); i++) {
////            Document doc = hits.doc(i);
////            System.out.println(doc.get("filename"));
////        }
////    }
////
////    public static void main(String[] args) throws IOException, ParseException{
////        new Searching("C:\\indexTest","潛陶").show();
////    }
//}
//
