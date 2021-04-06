package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Nettiauto extends AbstractPood
{

    public Nettiauto(PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        super("Nettiauto",poodRepository,kuulutusRepository);
    }

    @Override
    public List<Kuulutus> getKuulutused(){

        var tokenURL = "https://auth.nettix.fi/oauth2/token";

        JsonNode jsonResponse = Unirest.post(tokenURL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("grant_type", "client_credentials")
                .field("client_id", "anonymous")
                .field("client_secret", "anonymous")
                .asJson()
                .getBody();
        //System.out.println(jsonResponse.toPrettyString());
        String[] hulk = jsonResponse.toPrettyString().split(":");
        hulk = hulk[1].strip().split(",");
        String token = hulk[0];
        token = token.substring(1,token.length()-1);



        //kuulutuste request
        //nt:
        // GET /rest/car/search?page=1&rows=30&sortBy=dateCreated&sortOrder=desc&latitude=60.5346&longitude=25.6074&isMyFavorite=false&includeMakeModel=true&accessoriesCondition=and&isPriced=true&vatDeduct=true&taxFree=false&tagsCondition=and&isAdDealerExchange=false HTTP/1.1
        //  Host: api.nettix.fi
        //  accept: application/json
        //  X-Access-Token:
        //HEA REQUEST https://api.nettix.fi/rest/car/search?page=1&rows=30&sortBy=dateCreated&sortOrder=desc&status=forsale&identificationList=&isMyFavorite=false&vehicleType=1&includeMakeModel=true&bodyType=&accessoriesCondition=and&isPriced=true&vatDeduct=true&taxFree=false&roadWorthy=true&tagsCondition=and&isAdDealerExchange=false
        //CURL request curl -X GET "https://api.nettix.fi/rest/car/search?page=1&rows=30&sortBy=dateCreated&sortOrder=desc&status=forsale&identificationList=&isMyFavorite=false&vehicleType=1&includeMakeModel=true&bodyType=&accessoriesCondition=and&isPriced=true&vatDeduct=true&taxFree=false&roadWorthy=true&tagsCondition=and&isAdDealerExchange=false" -H "accept: application/json" -H "X-Access-Token: {token}"

        String pulliURL = "https://api.nettix.fi/rest/car/search?page=1&rows=10&sortBy=dateCreated&sortOrder=desc&status=forsale&identificationList=&isMyFavorite=false&vehicleType=1&includeMakeModel=true&bodyType=&accessoriesCondition=and&isPriced=true&vatDeduct=true&taxFree=false&roadWorthy=true&tagsCondition=and&isAdDealerExchange=false";
        JsonNode pull = Unirest.get(pulliURL)
                .header("accept", "application/json")
                .header("X-Access-Token",token)
                .asJson()
                .getBody();
        //System.out.println(pull);
        String firstID = "";
        String currentID = "";
        JSONArray array = pull.getArray();
        var newListings = new ArrayList<Kuulutus>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject kuulutus = array.getJSONObject(i);
            if(i==0)
                firstID = kuulutus.get("id").toString();
            currentID = kuulutus.get("id").toString();
            //System.out.println(praeguneID);
            //kontrollime kas oleme seda kuulutust juba näinud, kui oleme, siis lõpetame tsükli.
            if(kuulutus.get("adUrl").equals(getViimaneKuulutus())){
                break;
            }
            try {
                //mark
                JSONObject make = kuulutus.getJSONObject("make");
                String mark = make.get("name").toString();
                //mudel
                JSONObject mudla = kuulutus.getJSONObject("model");
                String model = mudla.get("name").toString();
                //aasta
                int year = parseInt(kuulutus.get("year").toString());
                //hind
                int price = parseInt(kuulutus.get("price").toString());
                //link
                String link = kuulutus.get("adUrl").toString();
                newListings.add(new Kuulutus(mark,model,year,price,link));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            //Kuulutus Auto = new Kuulutus(new String.,);
        }
        return newListings;
    }
}
