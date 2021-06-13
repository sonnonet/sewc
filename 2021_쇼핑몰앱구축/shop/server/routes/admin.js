
router.get('/clothes', (req, res) => {
    let page = req.query.page || 0
    let limit = 5

    clothes.findAndCountAll({
        raw: true,
        limit: limit,
        offset: page * limit
    }).then((result) => {
        res.json({
            totalCount: result.count,
            cloth: result.rows,
            limit: limit,
            currentPage: page
        })
    })
})

router.get('/users', (req, res) => {
    let page = req.query.page || 0
    let limit = 5

    users.findAndCountAll({
        raw: true,
        limit: limit,
        offset: page * limit
    }).then((result) => {
        res.json({
            totalCount: result.count,
            user: result.rows,
            limit: limit,
            currentPage: page
        })
    })
})
