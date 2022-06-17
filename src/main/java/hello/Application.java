package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@RestController
public class Application {

  static class Self {
    public String href;
  }

  static class Links {
    public Self self;
  }

  static class PlayerState {
    public Integer x;
    public Integer y;
    public String direction;
    public Boolean wasHit;
    public Integer score;
  }

  static class Arena {
    public List<Integer> dims;
    public Map<String, PlayerState> state;
  }

  static class ArenaUpdate {
    public Links _links;
    public Arena arena;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.initDirectFieldAccess();
  }

  @GetMapping("/")
  public String index() {
    return "Let the battle begin!!!";
  }

  @PostMapping("/**")
  public String index(@RequestBody ArenaUpdate arenaUpdate) {
    System.out.println("kkkkkkkk");


/*
    String jsonString = "{\"stat\": { \"sdr\": \"aa:bb:cc:dd:ee:ff\", \"rcv\": \"aa:bb:cc:dd:ee:ff\", \"time\": \"UTC in millis\", \"type\": 1, \"subt\": 1, \"argv\": [{\"type\": 1, \"val\":\"stackoverflow\"}]}}";
    JSONObject jsonObject = new JSONObject(jsonString);
    JSONObject newJSON = jsonObject.getJSONObject("arena");
    System.out.println(newJSON.toString());
    jsonObject = new JSONObject(newJSON.toString());
    System.out.println(jsonObject.getString("rcv"));
    System.out.println(jsonObject.getJSONArray("argv"));
*/

    //Our chess

    String me = "https://cloud-run-hackathon-java-springboot-ksvehvegcq-uc.a.run.app";

    int myX = arenaUpdate.arena.state.get(me).x;
    int myY = arenaUpdate.arena.state.get(me).y;
    String myDirection = arenaUpdate.arena.state.get(me).direction;
    boolean wasHit = arenaUpdate.arena.state.get(me).wasHit;
    int score = arenaUpdate.arena.state.get(me).score;

    System.out.println(myX+"   "+myY);

    String[] commands = new String[]{"F", "R", "L", "T"};
    int i = new Random().nextInt(4);
    return commands[i];
  }

}

