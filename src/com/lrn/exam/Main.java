package com.lrn.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0; // id를 위해 필요함!

    List<Article> articles = new ArrayList<>();

    makeTestData(articles); // 테스터용 데이터 생성 메서드

    if (articles.size() > 0) { // 데이터가 있을 때 마지막 게시물 번호 이후로 id 정하기
      articleLastId = articles.get(articles.size() - 1).id;
      // articles.size() - 1 == 마지막 게시물
    }

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("exit")) { // 종료 명령어
        System.out.println("== 프로그램 종료 ==");
        break;

      } else if (cmd.equals("/user/article/list")) { //게시글 목록 명령어
        System.out.println("---- 게시물 목록 ----");
        System.out.println("번호\t/\t제목");

        for (int i = articles.size() - 1 ; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d\t/\t%s\n", article.id, article.title);
        }
        System.out.println("--------------------");

      } else if (cmd.equals("/user/article/detail")) { //게시글 상세보기 명령어

        if (articles.isEmpty()) { // lastArticle == null, articles.size() == 0
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = articles.get(articles.size() - 1);

        System.out.println("-- 게시물 상세보기 --");
        System.out.printf("번호 : %s\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);

      } else if (cmd.equals("/user/article/write")) { //게시글 등록 명령어
        System.out.println("== 게시물 등록 ==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int id = articleLastId + 1;
        // int id = ++articleLastId;
        articleLastId++;
        //articleLastId = id;

        Article article = new Article(id, title, body);

        articles.add(article); // 새로 작성한 게시물 articles에 추가
        System.out.println("입력된 된 게시물 객체 : " + article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      } // if
      System.out.printf("입력된 명령어 : %s\n", cmd);
    } // while
    sc.close();
  } // main

  private static void makeTestData(List<Article> articles) {
    articles.add(new Article(1, "title01", "body01"));
    articles.add(new Article(2, "title02", "body02"));
    articles.add(new Article(3, "title03", "body03"));
  }// 테스터용 데이터 생성 메서드
} // Main

class Article {
  int id;
  String title;
  String body;

  public Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return String.format(
        "{id : %d, title : \"%s\", body : \"%s\"}",
        id, title, body);
  }
}