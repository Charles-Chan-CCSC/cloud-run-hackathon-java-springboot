package hello;

import org.json.JSONObject;
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

    public Integer getX() {return x;}

    public void setX(Integer x) {this.x = x;}

    public Integer getY() {return y;}

    public void setY(Integer y) {this.y = y;}

    public String getDirection() {return direction;}

    public void setDirection(String direction) {this.direction = direction;}

    public Boolean getWasHit() {return wasHit;}

    public void setWasHit(Boolean wasHit) {this.wasHit = wasHit;}

    public Integer getScore() {return score;}

    public void setScore(Integer score) {this.score = score;}
  }

  static class Arena {
    public List<Integer> dims;
    public Map<String, PlayerState> state;

    public List<Integer> getDims() {return dims;}

    public void setDims(List<Integer> dims) {this.dims = dims;}

    public Map<String, PlayerState> getState() {return state;}

    public void setState(Map<String, PlayerState> state) {this.state = state;}
  }

  static class ArenaUpdate {
    public Links _links;
    public Arena arena;

    public Links get_links() {return _links;}

    public void set_links(Links _links) {this._links = _links;}

    public Arena getArena() {return arena;}

    public void setArena(Arena arena) {this.arena = arena;}
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


/*
    String jsonString = "{\"stat\": { \"sdr\": \"aa:bb:cc:dd:ee:ff\", \"rcv\": \"aa:bb:cc:dd:ee:ff\", \"time\": \"UTC in millis\", \"type\": 1, \"subt\": 1, \"argv\": [{\"type\": 1, \"val\":\"stackoverflow\"}]}}";
    JSONObject jsonObject = new JSONObject(jsonString);
    JSONObject newJSON = jsonObject.getJSONObject("arena");
    System.out.println(newJSON.toString());
    jsonObject = new JSONObject(newJSON.toString());
    System.out.println(jsonObject.getString("rcv"));
    System.out.println(jsonObject.getJSONArray("argv"));
*/

    JSONObject jsonObject = new JSONObject(arenaUpdate.toString());

    JSONObject arena = jsonObject.getJSONObject("arena");
    System.out.println(arena.toString());

    JSONObject state = jsonObject.getJSONObject("state");
    System.out.println(state.toString());

    String me = "https://cloud-run-hackathon-java-springboot-ksvehvegcq-uc.a.run.app";
    JSONObject meJson = jsonObject.getJSONObject(me);
    int myX = meJson.getInt("x");
    int myY = meJson.getInt("y");

    System.out.println("hhhhhhhhhhhhhh");
    System.out.println(myX+"   "+myY);
    System.out.println("hhhhhhhhhhhhhh");

    /*
    String myDirection = arenaUpdate.arena.state.get(me).direction;
    boolean wasHit = arenaUpdate.arena.state.get(me).wasHit;
    int score = arenaUpdate.arena.state.get(me).score;
*/


    String[] commands = new String[]{"F", "R", "L", "T"};
    int i = new Random().nextInt(4);
    return commands[i];
  }

}

