package nsgl.evolve.es;

import nsgl.generic.Tagged;
import nsgl.search.Goal;
import nsgl.search.RealValuedGoal;
import nsgl.search.population.RealValuedPopulationGoal;
import nsgl.search.population.VariationReplacePopulationSearch;
import nsgl.search.space.Space;
import nsgl.search.variation.Variation_1_1;
import nsgl.search.variation.Variation_n_1;

public class ESStep<T,P> extends VariationReplacePopulationSearch<T,Double>{
	protected Space<P> s_space;
	protected Goal<T,Double> goal;
	
	public ESStep(int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
       		ESReplacement<T> replacement ){
		super( 	mu, new ESVariation<T,P>(lambda, ro, y_recombination, mutation, s_recombination, s_mutation), 
				replacement);
		this.s_space = s_space;
	}

	public ESStep(int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
       		boolean plus_replacement ){
		this(	mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, 
				plus_replacement? new PlusReplacement<T>(mu):new CommaReplacement<T>(mu) );
	}
	
	@Override
	public Tagged<T>[] init(Space<T> space) {
    	Tagged<T>[] pop = super.init(space);
    	for( int i=0; i<pop.length; i++ ){
    		pop[i].setTag(ESVariation.PARAMETERS_OPERATOR, s_space.pick() );
    	}
    	return pop;
	}

	@Override
	public Goal<T, Double> goal() { return goal; }

	@Override
	public void setGoal(Goal<T, Double> goal) { this.goal = goal; }

	@Override
	public Tagged<T> pick(Tagged<T>[] pop) {
		RealValuedPopulationGoal<T> g = new RealValuedPopulationGoal<T>();
		return g.pick(pop, (RealValuedGoal<T>)goal);
	}
}