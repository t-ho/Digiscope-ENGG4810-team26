package core;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Parameters;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class Evaluator extends DoubleEvaluator {

	private static final Parameters PARAMETERS;
	private StaticVariableSet<Double> variables;

	static {
		PARAMETERS = DoubleEvaluator.getDefaultParameters();
		PARAMETERS.setTranslation(DoubleEvaluator.PI, "\u03C0");
	}

	public Evaluator() {
		super(PARAMETERS);
		variables = new StaticVariableSet<Double>();
	}

	/**
	 * Set variable value
	 * @param variableName The name of variable
	 * @param value The value of variable
	 */
	public void setVariableValue(String variableName, Double value){
		variables.set(variableName, value);
	}
	
	public StaticVariableSet<Double> getVariables() {
		return variables;
	}
}
