package hello.core.singletone;

public class SingletoneService {

    // 1. static 영역에 객체를 딱 1개만 설정해둔다
    private static final SingletoneService instance=new SingletoneService();

    // 2. public으로 열어서 객체 인스턴스가 필요한 경우 static 메서드를 통해서만 이용할 수 있도록 한다
    public static SingletoneService getInstance() {
        return instance;
    }
    //3. 생성자를 private으로 선언해서 외부에서 new를 해서 객체를 생성할 수 없도록 한다.
    private SingletoneService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
