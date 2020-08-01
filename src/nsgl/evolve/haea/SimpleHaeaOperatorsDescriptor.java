package nsgl.evolve.haea;
import nsgl.descriptor.Descriptor;
import nsgl.real.Statistics;
import nsgl.real.matrix.Matrix;


/**
 * <p>Title: HaeaStrategyDescriptors</p>
 * <p>Description: Descriptors for the HAEA offspring generation strategy.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class SimpleHaeaOperatorsDescriptor<T> implements Descriptor{
    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param tr HAEAStrategy object to be described
     */
    public double[] features( HaeaOperators<T> operators ) {
    	double[][] rates = new double[operators.rates.size()][];
    	try{ for( int i=0; i<rates.length; i++ ) rates[i] = operators.rates(i); }catch(Exception e){}
		Statistics[] stat = Matrix.statistics(rates, false);
		double[] avg = new double[stat.length];
		for( int i=0; i<avg.length; i++ ) avg[i] = stat[i].avg; 
		return avg;
    }

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object obj){ return features((HaeaOperators<T>)obj);	}
}