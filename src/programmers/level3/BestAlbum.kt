package programmers.level3

/**
 * 문제 주소: https://school.programmers.co.kr/learn/courses/30/lessons/42579
 */
class BestAlbum {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer = mutableListOf<Int>()

        val genrePlayMap = mutableMapOf<String, PlayInfo>()

        for ((index, genre) in genres.withIndex()) {
            val playInfo = genrePlayMap.getOrPut(genre) { PlayInfo() }

            val playCount = plays[index]

            playInfo.addTotalPlays(playCount)
            playInfo.putPlayMap(index, playCount)
        }

        genrePlayMap.toList().sortedByDescending { it.second.totalPlays }.forEach { it ->
            val playInfo = it.second
            val playMap = playInfo.playMap

            playMap.toList().sortedByDescending { it.second }.forEachIndexed { index, pair ->
                if (index < 2) {
                    answer.add(pair.first)
                }
            }
        }

        return answer.toIntArray()
    }

    data class PlayInfo (
        var totalPlays: Int = 0,
        val playMap: MutableMap<Int, Int> = mutableMapOf()
    ) {
        fun addTotalPlays(play: Int) {
            totalPlays += play
        }

        fun putPlayMap(index: Int, play: Int) {
            playMap[index] = play
        }
    }
}