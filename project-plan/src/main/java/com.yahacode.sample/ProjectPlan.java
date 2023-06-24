package com.yahacode.sample;

public class ProjectPlan {

    private String name;

    private Position sa;

    private Position web;

    private Position server;

    private Position test;

    private Position om;

    private int period;

    public void display(int projectCount) {
        System.out.println("\n===== " + name + " 级项目 =====");
        double juniorCount = sa.juniorCount + web.juniorCount + server.juniorCount + test.juniorCount + om.juniorCount;
        double intermediateCount =
                sa.intermediateCount + web.intermediateCount + server.intermediateCount + test.intermediateCount + om.intermediateCount;
        double seniorCount = sa.seniorCount + web.seniorCount + server.seniorCount + test.seniorCount + om.seniorCount;
        double moneyCost =
                (juniorCount * SalaryCost.JUNIOR + intermediateCount * SalaryCost.INTERMEDIATE + seniorCount * seniorCount) * period;
        System.out.println("单项目成本：" + moneyCost);
        double singleProjectManPower = (juniorCount + intermediateCount + seniorCount) * period;
        System.out.println("单项目人力：" + singleProjectManPower + "人月");
        System.out.println("一年做 " + projectCount + " 个项目， 总计人力：" + singleProjectManPower * projectCount + " 人月， " +
                "所需开发人数：" + singleProjectManPower * projectCount / 12);
        System.out.println("其中，初级：" + (juniorCount * period * projectCount / 12) + "个，中级：" + (intermediateCount * period * projectCount / 12) + "个，高级：" + (seniorCount * period * projectCount / 12) + "个");
        System.out.println("其中，SA：" + sa.total() * period * projectCount / 12 + "个， 前端：" + web.total() * period * projectCount / 12 + "个， 后端：" + server.total() * period * projectCount / 12 + "个， 测试：" + test.total() * period * projectCount / 12 +
                "个， 运维：" + om.total() * period * projectCount / 12 + "个");
    }

    public ProjectPlan(String name, Position sa, Position web, Position server, Position test, Position om,
                       int period) {
        this.name = name;
        this.sa = sa;
        this.web = web;
        this.server = server;
        this.test = test;
        this.om = om;
        this.period = period;
    }

    public static void main(String[] args) {
        ProjectPlan projectPlan1 = new ProjectPlan("20w",
                new Position(0, 0, 0),
                new Position(1, 0, 0),
                new Position(2, 0, 0),
                new Position(0, 0, 0),
                new Position(0, 0, 0), 3);
        projectPlan1.display(10);

        ProjectPlan projectPlan2 = new ProjectPlan("50w",
                new Position(0.5, 0, 0),
                new Position(1.5, 0, 0),
                new Position(4, 0.5, 0),
                new Position(0.5, 0, 0),
                new Position(0.5, 0, 0), 3);
        projectPlan2.display(6);

        ProjectPlan projectPlan3 = new ProjectPlan("100w",
                new Position(0, 0.5, 0),
                new Position(2, 0.5, 0),
                new Position(6, 1, 1),
                new Position(1, 0.5, 0),
                new Position(0.5, 0, 0), 3);
        projectPlan3.display(2);
    }
}
