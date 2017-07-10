import org.lenskit.api.ItemScorer
import org.lenskit.baseline.*
import org.grouplens.lenskit.iterative.IterationCount
import org.lenskit.mf.funksvd.FeatureCount
import org.lenskit.mf.funksvd.FunkSVDItemScorer
import org.lenskit.hybrid.*
import org.lenskit.api.ItemScorer
import org.lenskit.basic.PopularityRankItemScorer

algorithm {
    bind ItemScorer to FunkSVDItemScorer
    bind ItemRecommender to RankBlendingItemRecommender
    within (RankBlendingItemRecommender.Right, ItemRecommender) {
        bind ItemScorer to PopularityRankItemScorer
    }
    set BlendWeight to 0.5
    bind (BaselineScorer, ItemScorer) to UserMeanItemScorer
    bind (UserMeanBaseline, ItemScorer) to ItemMeanRatingItemScorer
    set IterationCount to 125
    set FeatureCount to 25
}
