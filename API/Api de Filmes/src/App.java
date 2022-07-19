import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // fazer a conexão HTTP e buscar os 250 filmes
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=ee4b2ac5da8f5be482301468e7d0292f";
        var client = HttpClient.newHttpClient();

        URI endereco = URI.create(url);
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = (String) response.body();
        // System.out.println(body);

        // pegar dados da api do the moviedb(titulo,imagem,classificação)
        var parser = new JsonParser();

        List<Map<String, String>> listaFilme = parser.parse(body);
       // System.out.println(listaFilme.size());
       
        for (Map<String, String> filme : listaFilme) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("backdrop_path"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }

    }
}
