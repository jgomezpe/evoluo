package nsgl.evolve.ga;

import nsgl.generic.Tagged;
import nsgl.search.Goal;
import nsgl.search.RealValuedGoal;
import nsgl.search.population.Generational;
import nsgl.search.population.PopulationReplacement;
import nsgl.search.population.RealValuedPopulationGoal;
import nsgl.search.population.TotalSelectionReplacement;
import nsgl.search.population.VariationReplacePopulationSearch;
import nsgl.search.selection.Selection;
import nsgl.search.variation.Variation_1_1;
import nsgl.search.variation.Variation_2_2;

/**
 * <p>Title: GeneticAlgorithm</p>
 *
 * <p>Description: The Genetic Algorithm evolutionary transformation</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GAStep<T> extends VariationReplacePopulationSearch<T,Double>{
	protected Selection<T> selection;
	protected GAVariation<T> variation;
	protected Goal<T, Double> goal;
	
    public GAStep( int mu, Selection<T> selection,
    		Variation_1_1<T> mutation, Variation_2_2<T> xover,
            double probability, PopulationReplacement<T> replace ) {
    	super( mu, new GAVariation<T>(selection, mutation, xover, probability), replace);
    } 
    
    public GAStep( int mu, Selection<T> selection,
    		Variation_1_1<T> mutation, Variation_2_2<T> xover,
            double probability, boolean generational ) {
    	super( 	mu, new GAVariation<T>(selection, mutation, xover, probability), 
    			generational?new Generational<T>():new TotalSelectionReplacement<T>());
    }

	@Override
	public Goal<T, Double> goal(){ return goal; }

	@Override
	public void setGoal(Goal<T, Double> goal) { this.goal = goal; }

	@Override
	public Tagged<T> pick(Tagged<T>[] pop) {
		RealValuedPopulationGoal<T> g = new RealValuedPopulationGoal<T>();
		return g.pick(pop, (RealValuedGoal<T>)goal);
	}
}