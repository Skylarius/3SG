class Note (var pitch: Int, var time: Float) {
  //Per stampe di prova
  override def toString: String =
    s"(Altezza MIDI: $pitch, Durata: $time)"

  //Funzione di Pattern Matching per individuare il nome della nota
  //Alcuni dei pitch corrispondono ai sette gradi principali (do, re, mi...),
  //mentre i rimanenti sono gradi alterati (do diesis, re diesis,..) per
  //riconoscerli si vede il grado precedente: avrà il suo nome e diesis a suffisso
  def getNoteName(pitch: Int): String = {
    pitch%12 match {
      case 0 => "DO"
      case 2 => "RE"
      case 4 => "MI"
      case 5 => "FA"
      case 7 => "SOL"
      case 9 => "LA"
      case 11 => "SI"
      case _  => getNoteName(pitch-1)+"#"
    }
  }

  def getNoteName(): String = {
    getNoteName(this.pitch); //overloading del metodo per ragioni di ricorsione
  }

  //Funzione di Pattern Matching per individuare l'ottava della nota
  //L'ottava di riferimento è calcolata facendo il rapporto tra il pitch e 12 (l'insieme dei semitoni dentro l'ottava) e sottraendo 1 per ragioni di convenzione
  def getOctave(): Int = {
    this.pitch/12 match {
      case x => x-1
    }
  }

  //Funzione di Pattern Matching per identificare il tipo della nota (Figura)
  //NB.: Va approfondita
  def findNoteType(bpm: Int): String = {
    this.time.toInt.toFloat/(60000/bpm).toFloat match {
      case 4f => "Semibreve"
      case 3f => "Minima Puntata"
      case 2f => "Minima"
      case 1.5f => "Semiminima Puntata"
      case 1f => "Semiminima"
      case 0.75f => "Croma Puntata"
      case 0.5f => "Croma"
      case 0.375 => "Semicroma Puntata"
      case 0.25f => "Semicroma"
      case 0.1875f => "Biscroma Puntata"
      case 0.125f => "Biscroma"
      case _ => "Nunno sacciu"
    }
  }

}
