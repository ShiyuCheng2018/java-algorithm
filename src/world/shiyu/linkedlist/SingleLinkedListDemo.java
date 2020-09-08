package world.shiyu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        HeroNode hero_1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero_2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero_3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero_4 = new HeroNode(4, "林冲", "豹子头");

        SingleLikedList singleLinkedList= new SingleLikedList();
        singleLinkedList.add(hero_1);
        singleLinkedList.add(hero_2);
        singleLinkedList.add(hero_3);
        singleLinkedList.add(hero_4);

        singleLinkedList.list();
    }
}
