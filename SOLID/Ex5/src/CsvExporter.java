import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(String title, String body) {

        String normalized =
                body.replace("\n", " ")
                    .replace(",", " ");

        String csv = "title,body\n" +
                title + "," + normalized + "\n";

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }
}