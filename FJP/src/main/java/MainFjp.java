import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class MainFjp {
    private static String ROOT_SITE = "https://skillbox.ru/";

    public static void main(String[] args) throws IOException {
        SitemapNode sitemapRoot = new SitemapNode(ROOT_SITE);
        new ForkJoinPool().invoke(new SitemapNodeRecursiveAction(sitemapRoot));

        FileOutputStream stream = new FileOutputStream("C:\\Users\\Yakov\\IdeaProjects\\HomeWork\\Multithreading\\FJP\\src\\main\\resources\\site.txt");
        String result = createSitemapString(sitemapRoot, 0);
        stream.write(result.getBytes());

        stream.flush();
        stream.close();
    }

    public static String createSitemapString(SitemapNode node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createSitemapString(child, depth + 1));
        });
        return result.toString();
    }
}


