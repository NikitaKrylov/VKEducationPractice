package com.example.vknext.core.data.repositories.forms

import com.example.vknext.core.data.models.FeedBackForm
import com.example.vknext.core.data.models.FeedBackGroup
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.core.data.models.FeedBackType
import javax.inject.Inject

class FormsFeedbackRepositoryImpl @Inject constructor() : FormsFeedbackRepository {
    override fun getAll(): List<FeedBackForm> {
        return forms
    }

    override fun getByType(type: FeedBackType): FeedBackForm? {
        return forms.find { it.type == type }
    }


    companion object {
        private val forms = listOf(
            FeedBackForm(
                type = FeedBackType.THANKS,
                groups = listOf(
                    FeedBackGroup(
                        hashtag = "Вместе",
                        points = listOf(
                            FeedBackPoint(
                                id = 1,
                                name = "учитываешь интересы разных вертикалей и горизонталей"
                            ),
                            FeedBackPoint(
                                id = 2,
                                name = "делишься опытом, идеями и обрабной связью"
                            ),
                        )
                    ),
                    FeedBackGroup(
                        hashtag = "ПроКлиента",
                        points = listOf(
                            FeedBackPoint(
                                id = 3,
                                name = "взаимодействуешь уважилтельно, открыто и честно"
                            ),
                            FeedBackPoint(
                                id = 4,
                                name = "делаешь жизнь других лучше и проще"
                            ),
                        )
                    ),
                    FeedBackGroup(
                        hashtag = "БериДействуй",
                        points = listOf(
                            FeedBackPoint(
                                id = 5,
                                name = "берешь ответственность от начала до конца"
                            ),
                            FeedBackPoint(
                                id = 6,
                                name = "оперативно и продуманно решаешь проблемы"
                            ),
                        )
                    ),
                    FeedBackGroup(
                        hashtag = "СделайКруто",
                        points = listOf(
                            FeedBackPoint(
                                id = 7,
                                name = "показываешь крутой результат"
                            ),
                            FeedBackPoint(
                                id = 8,
                                name = "пробуешь нвоое и готов развиваться"
                            ),
                        )
                    ),
                    FeedBackGroup(
                        hashtag = "БыстроПоСути",
                        points = listOf(
                            FeedBackPoint(
                                id = 9,
                                name = "ищешь простые пути решения и сокращаешь бюрократию"
                            ),
                            FeedBackPoint(
                                id = 10,
                                name = "четко и понятно объясняешь"
                            ),
                        )
                    ),
                    FeedBackGroup(
                        hashtag = "БольшеЧемРабота",
                        points = listOf(
                            FeedBackPoint(
                                id = 11,
                                name = "вкладываешься на полную в то, что делаешь"
                            ),
                            FeedBackPoint(
                                id = 12,
                                name = "достигаешь цели несмотря на препятствия"
                            ),
                        )
                    ),
                )
            )
        )
    }
}