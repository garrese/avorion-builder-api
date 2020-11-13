import avuilder4j.design.BlockPlan

evaluate(new File("Test2.groovy"))

public static BlockPlan test(Integer arg, BlockPlan plan) {
	println "test:"+arg;

	if(plan == null)
		plan = new BlockPlan();

	return plan;
}

