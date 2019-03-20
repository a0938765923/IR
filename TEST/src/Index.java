import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Index
{
    private String path;
    public Directory openPath;
    public Index(String s, String openFile)throws IOException {
        openPath=FSDirectory.open(Paths.get(openFile));
        path = s;
    }

    public void createIndex(List<String> parse_result) throws IOException {
        System.out.println("開始建立索引檔案……");
        Analyzer analyzer = new StandardAnalyzer();
        Directory indexDir = FSDirectory.open(Paths.get(path));
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = null;
        try {
            //建立 IndexWriter
            indexWriter = new IndexWriter(indexDir, iwc);
            System.out.println("開始建立索引文件資訊……");

            //建立索引檔案
            addDocument(indexWriter, parse_result);

//            docId++;
            System.out.println("索引檔案建立完成！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("索引檔案建立失敗！");
        } finally {
            //優化索引並關閉Writer
            if (indexWriter != null)
                indexWriter.close();
        }

    }

    private void addDocument(IndexWriter writer, List<String> goods) throws IOException
    {
        Document document;
        for (int i = 0; i < goods.size(); i++) {
            document = new Document();
            document.add(new Field("token", goods.get(i), TextField.TYPE_STORED));
            document.add(new TextField("pos", String.valueOf(i), Field.Store.YES));
            document.add(new TextField("docId", String.valueOf(i), Field.Store.YES));
            System.out.println(document);
            writer.addDocument(document);
        }

    }

    public String search(String target)throws Exception{
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Query q = new QueryParser("token", analyzer).parse(target);
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(openPath);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        System.out.println(docs.scoreDocs.length);

        List<String>pos=new ArrayList<>();
        for(int i=0;i<docs.scoreDocs.length;i++){
            int doc = docs.scoreDocs[i].doc;
            Document doc2 = searcher.doc(doc);

//            System.out.println("------>"+doc2.getField("token"));
//            System.out.println("------>"+doc2.getField("pos"));
            pos.add(doc2.getField("pos").stringValue());
//            System.out.println("------>"+doc2.getField("docId"));
        }
        String result = target + " , "+pos;
        System.out.println(result);
        return "return what?";
    }
}